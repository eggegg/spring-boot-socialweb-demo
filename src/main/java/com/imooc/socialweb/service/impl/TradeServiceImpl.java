package com.imooc.socialweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.socialweb.pojo.Sku;
import com.imooc.socialweb.pojo.Trade;
import com.imooc.socialweb.mapper.TradeMapper;
import com.imooc.socialweb.service.SkuService;
import com.imooc.socialweb.service.StockService;
import com.imooc.socialweb.service.TradeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-13
 */
@Service
public class TradeServiceImpl extends ServiceImpl<TradeMapper, Trade> implements TradeService {

    @Autowired
    private SkuService skuService;

    @Autowired
    private StockService stockService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Trade createTrade(Trade trade) {

        Sku sku = skuService.getById(trade.getSkuId());
        if (sku == null){
            return null;
        }
        if (sku.getPrice().compareTo(trade.getSinglePrice()) != 0) {
            return null;
        }
        boolean stockDecreaseSuccess = stockService.decreaseStock(trade.getSkuId(), trade.getShopId(), trade.getStockCount());
        if (!stockDecreaseSuccess){
            throw new RuntimeException("扣件库存失败");
        }

        trade.setStatus(1);
        save(trade);

        //去第三方支付公司去下单
        String wechatTradeId = UUID.randomUUID().toString();
        trade.setWechatTradeId(wechatTradeId);
        updateById(trade);

        return trade;
    }

    @Override
    @Transactional
    public Trade pay(Trade trade) {
        QueryWrapper<Trade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("wechat_trade_id", trade.getWechatTradeId());
        queryWrapper.last("for update");
        trade = getBaseMapper().selectOne(queryWrapper);
        if (trade == null ){
            return null;
        }
        if (trade.getStatus() == 2) {
            //已经发起回调,保持幂等
            return trade;
        }
        if (trade.getStatus() == 3) {
            return  exceptionPay(trade, 2);
        }

        trade.setStatus(2);
        updateById(trade);

        return trade;
    }

    private Trade exceptionPay(Trade trade, int type){
        if (type == 1) {
            //优先取消
            if (refund(trade.getWechatTradeId())) {
                return trade;
            } else {
                return null;
            }
        } else {
            //优先支付
            boolean decreaseSuccess = stockService.decreaseStock(trade.getSkuId(),trade.getShopId(),trade.getStockCount());
            if (!decreaseSuccess){
                if (refund(trade.getWechatTradeId())) {
                    return trade;
                } else {
                    return null;
                }
            } else {
                trade.setStatus(2);
                updateById(trade);
                return trade;
            }
        }
    }

    private boolean refund(String wechatTradeid){
        //调用微信退款
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Trade cancel(Trade trade) {
        QueryWrapper<Trade> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id", trade.getId());
        queryWrapper.last("for update");
        trade = getBaseMapper().selectOne(queryWrapper);
        if (trade == null){
            return  null;
        }
        if (trade.getStatus() == 3){
            return trade; // 已经成功，保持幂等
        }
        if (trade.getStatus() == 2){
            return null;
        }
        boolean isSuccess = stockService.tradeRollbackStock(trade.getSkuId(), trade.getShopId(), trade.getStockCount());
        if (!isSuccess){
            throw new RuntimeException("库存回滚失败");
        }
        trade.setStatus(3);
        updateById(trade);
        return trade;
    }
}
