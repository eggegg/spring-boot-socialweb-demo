package com.imooc.socialweb.mapper;

import com.imooc.socialweb.pojo.Stock;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
public interface StockMapper extends BaseMapper<Stock> {
    int decreaseStock(@Param("skuId")Integer skuId, @Param("shopId")Integer shopId, @Param("stockCount")Integer stockCount);
    int tradeRollbackStock(@Param("skuId")Integer skuId, @Param("shopId")Integer shopId, @Param("stockCount")Integer stockCount);
}
