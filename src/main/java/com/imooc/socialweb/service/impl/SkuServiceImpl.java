package com.imooc.socialweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.socialweb.pojo.Sku;
import com.imooc.socialweb.mapper.SkuMapper;
import com.imooc.socialweb.pojo.Stock;
import com.imooc.socialweb.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku> implements SkuService {

    @Autowired
    private SkuAttributeInfoService skuAttributeInfoService;

    @Autowired
    private AttributeNameService attributeNameService;

    @Autowired
    private AttributeValueService attributeValueService;

    @Autowired
    private StockService stockService;

    @Override
    public List<Sku> listByShopId(QueryWrapper<Sku> queryWrapper, Integer shopId) {
        List<Sku> objectList = super.list(queryWrapper);
        objectList.forEach(obj->{
            QueryWrapper attributeQueryWrapper = new QueryWrapper();
            attributeQueryWrapper.eq("sku_id", obj.getId());

            obj.setSkuAttributeInfoList(skuAttributeInfoService.list(attributeQueryWrapper));
            obj.getSkuAttributeInfoList().forEach(attributeInfo->{
                attributeInfo.setAttributeName(attributeNameService.getById(attributeInfo.getAttributeNameId()).getName());
                attributeInfo.setAttributeValue(attributeValueService.getById(attributeInfo.getAttributeValueId()).getValue());
            });

            QueryWrapper<Stock> queryWrapperStock = new QueryWrapper<>();
            queryWrapperStock.eq("sku_id", obj.getId());
            queryWrapperStock.eq("shop_id", shopId);
            List<Stock> stockList = stockService.list(queryWrapperStock);
            if (stockList.size() > 0) {
                obj.setStockCount(stockList.get(0).getStockCount());
            } else {
                obj.setStockCount(0);
            }
        });

        return objectList;
    }
}
