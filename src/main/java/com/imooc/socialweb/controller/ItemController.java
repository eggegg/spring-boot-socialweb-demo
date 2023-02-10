package com.imooc.socialweb.controller;


import com.imooc.socialweb.base.JsonReturnType;
import com.imooc.socialweb.pojo.Item;
import com.imooc.socialweb.pojo.Sku;
import com.imooc.socialweb.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
@RestController
@RequestMapping("/item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @RequestMapping(value = "create",method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType create(@RequestBody Item item) {
        itemService.save(item);
        return JsonReturnType.createType(item);
    }

    @RequestMapping(value = "createsku",method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType createsku(@RequestBody Sku sku) {
        itemService.createSku(sku);
        return JsonReturnType.createType(sku);
    }

    @RequestMapping(value = "get",method = {RequestMethod.GET})
    @ResponseBody
    public JsonReturnType get(@RequestParam(name = "id")Integer id,
                              @RequestParam(name = "shopId")Integer shopId) {
        Item item = itemService.getItem(id, shopId);
        return JsonReturnType.createType(item);
    }

    @RequestMapping(value = "search",method = {RequestMethod.GET})
    @ResponseBody
    public JsonReturnType get(@RequestParam(name = "name")String name,
                              @RequestParam(name = "shopId")Integer shopId) {
        List<Item> itemList = itemService.search(name, shopId);
        return JsonReturnType.createType(itemList);
    }
}
