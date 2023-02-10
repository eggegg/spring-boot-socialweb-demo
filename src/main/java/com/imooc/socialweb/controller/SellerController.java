package com.imooc.socialweb.controller;


import com.imooc.socialweb.base.JsonReturnType;
import com.imooc.socialweb.pojo.Seller;
import com.imooc.socialweb.service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
@RestController
@RequestMapping("/seller")
public class SellerController {

    @Autowired
    private SellerService sellerService;

    @RequestMapping(value = "create", method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType create(@RequestBody Seller seller){
        sellerService.save(seller);
        return JsonReturnType.createType(seller);
    }
}
