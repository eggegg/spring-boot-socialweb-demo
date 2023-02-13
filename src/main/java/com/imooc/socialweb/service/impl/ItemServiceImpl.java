package com.imooc.socialweb.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.imooc.socialweb.helper.RedisOperation;
import com.imooc.socialweb.pojo.Item;
import com.imooc.socialweb.mapper.ItemMapper;
import com.imooc.socialweb.pojo.Seller;
import com.imooc.socialweb.pojo.Sku;
import com.imooc.socialweb.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
@Service
public class ItemServiceImpl extends ServiceImpl<ItemMapper, Item> implements ItemService {

    @Autowired
    private SkuService skuService;

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private RedisOperation redisOperation;

    @Autowired
    private SkuAttributeInfoService skuAttributeInfoService;

    @Autowired
    private BrandService brandService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SellerService sellerService;

    @Override
    @Transactional
    public Sku createSku(Sku sku) {
        skuService.save(sku);
        sku.getSkuAttributeInfoList().forEach(skuAttributeInfo -> {
            skuAttributeInfo.setSkuId(sku.getId());
            skuAttributeInfoService.save(skuAttributeInfo);
        });
        return sku;
    }

    @Override
    public Item getItem(Integer id, Integer shopId) {


        //string缓存方案

        Item item = null;
        String itemRedisKey = "itemId_"+id;
        String skuRedisKey = "itemId_"+id+"_skuList_shopId_"+shopId;

        item = (Item) redisTemplate.opsForValue().get(itemRedisKey);
        if (item == null) {
            item = getById(id);
            if (item == null) {
                //如何防止缓存穿透
                return null;
            }

            item.setSellerName(sellerService.getById(item.getSellerId()).getSellerName());
            item.setBrand(brandService.getById(item.getBrandId()).getName());
            item.setCategory(categoryService.getById(item.getCategoryId()).getName());
            //如何防止缓存雪崩
            redisOperation.asyncRedisStringSet(itemRedisKey,item,10, TimeUnit.MINUTES);
//            redisTemplate.opsForValue().set(itemRedisKey,item,10, TimeUnit.MINUTES);
        }

        //取sku
        List<Sku> skuList = (List<Sku>) redisTemplate.opsForValue().get(skuRedisKey);
        if (skuList == null){
            QueryWrapper<Sku> skuQueryWrapper = new QueryWrapper<>();
            skuQueryWrapper.eq("item_id", item.getId());
            skuList = skuService.listByShopId(skuQueryWrapper,shopId);

            redisOperation.asyncRedisStringSet(skuRedisKey,skuList,10,TimeUnit.MINUTES);
//            redisTemplate.opsForValue().set(skuRedisKey,skuList,10,TimeUnit.MINUTES);
        }

        item.setSkuList(skuList);

        return item;

        // 数据库访问方案
//        Item item = getById(id);
//        if (item == null) {
//            return null;
//        }
//
//        item.setSellerName(sellerService.getById(item.getSellerId()).getSellerName());
//        item.setBrand(brandService.getById(item.getBrandId()).getName());
//        item.setCategory(categoryService.getById(item.getCategoryId()).getName());
//
//        QueryWrapper<Sku> skuQueryWrapper = new QueryWrapper<>();
//        skuQueryWrapper.eq("item_id", item.getId());
//        List<Sku> skuList = skuService.listByShopId(skuQueryWrapper,shopId);
//        item.setSkuList(skuList);
//        return item;
    }

    @Override
    public List<Item> search(String name, Integer shopId) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.likeRight("name", name);
        queryWrapper.select("id");
        List<Long> itemIdList = super.listObjs(queryWrapper);
        List<Item> itemList = itemIdList.stream().map(obj->{
            return getItem(obj.intValue(),shopId);
        }).collect(Collectors.toList());

        return itemList;
    }
}
