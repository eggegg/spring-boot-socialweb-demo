package com.imooc.socialweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.socialweb.pojo.Stock;
import com.imooc.socialweb.mapper.StockMapper;
import com.imooc.socialweb.service.StockService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
@Service
public class StockServiceImpl extends ServiceImpl<StockMapper, Stock> implements StockService {

    @Override
    @Transactional
    public Stock increaseStock(Integer skuId, Integer shopId, Integer stockCount) {
        // 快照读
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.eq("sku_id", skuId);
        wrapper.eq("shop_id", shopId);
        Stock stock = getBaseMapper().selectOne(wrapper);
        if (stock == null){
            // 如果高并发下，两个并发新增，也需要try catch
            try{
                stock = new Stock();
                stock.setSkuId(skuId);
                stock.setShopId(shopId);
                stock.setStockCount(stockCount);
                save(stock);
            }catch (DuplicateKeyException ex) {
                updateStock(skuId, shopId, stockCount);
            }

        } else {
            // 悲观锁，快速原子修改，因为有sku_id,shop_id的唯一索引，不会产生间隙锁（前提是索引存在）
            updateStock(skuId, shopId, stockCount);
        }
        return stock;
    }

    @Override
    public boolean decreaseStock(Integer skuId, Integer shopId, Integer stockCount) {
        int affectedRow = getBaseMapper().decreaseStock(skuId, shopId, stockCount);
        return affectedRow > 0 ? true : false;
    }

    @Override
    public boolean tradeRollbackStock(Integer skuId, Integer shopId, Integer stockCount) {
        int affectedRow = getBaseMapper().tradeRollbackStock(skuId, shopId, stockCount);
        return affectedRow > 0 ? true : false;
    }

    private void updateStock(Integer skuId, Integer shopId, Integer stockCount){
        QueryWrapper<Stock> wrapper = new QueryWrapper<>();
        wrapper.eq("sku_id", skuId);
        wrapper.eq("shop_id", shopId);
        wrapper.last("for update");
        Stock stock = getBaseMapper().selectOne(wrapper);
        stock.setStockCount(stock.getStockCount().intValue() + stockCount);
        updateById(stock);
    }
}
