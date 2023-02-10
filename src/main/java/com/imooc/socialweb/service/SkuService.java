package com.imooc.socialweb.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.socialweb.pojo.Sku;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
public interface SkuService extends IService<Sku> {
    List<Sku> listByShopId(QueryWrapper<Sku> queryWrapper, Integer shopId);
}
