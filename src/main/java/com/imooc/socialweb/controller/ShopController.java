package com.imooc.socialweb.controller;


import com.imooc.socialweb.base.JsonReturnType;
import com.imooc.socialweb.pojo.Shop;
import com.imooc.socialweb.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
@RestController
@RequestMapping("/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @RequestMapping(value = "create",method = {RequestMethod.POST})
    public JsonReturnType create(@RequestBody Shop shop){
        shopService.save(shop);
        return JsonReturnType.createType(shop);
    }

}
