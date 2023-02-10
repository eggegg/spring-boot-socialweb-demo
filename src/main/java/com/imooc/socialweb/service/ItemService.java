package com.imooc.socialweb.service;

import com.imooc.socialweb.pojo.Item;
import com.baomidou.mybatisplus.extension.service.IService;
import com.imooc.socialweb.pojo.Sku;
import org.omg.PortableInterceptor.Interceptor;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
public interface ItemService extends IService<Item> {
    Sku createSku(Sku sku);

    Item getItem(Integer id, Integer shopId);

    List<Item> search(String name, Integer shopId);
}
