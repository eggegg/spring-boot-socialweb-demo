package com.imooc.socialweb.service;

import com.imooc.socialweb.pojo.Trade;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-13
 */
public interface TradeService extends IService<Trade> {

    Trade createTrade(Trade trade);
    Trade pay(Trade trade);
    Trade cancel(Trade trade);
}
