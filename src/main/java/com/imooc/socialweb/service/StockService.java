package com.imooc.socialweb.service;

import com.imooc.socialweb.pojo.Stock;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
public interface StockService extends IService<Stock> {

    Stock increaseStock(Integer skuId, Integer shopId, Integer stockCount);

    boolean decreaseStock(Integer skuId, Integer shopId, Integer stockCount);

    boolean tradeRollbackStock(Integer skuId, Integer shopId, Integer stockCount);

}
