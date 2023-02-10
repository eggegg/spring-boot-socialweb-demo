package com.imooc.socialweb.controller;


import com.imooc.socialweb.base.JsonReturnType;
import com.imooc.socialweb.pojo.Brand;
import com.imooc.socialweb.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-10
 */
@RestController
@RequestMapping("/brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping(value = "create", method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType create(@RequestBody Brand brand) {
        brandService.save(brand);
        return JsonReturnType.createType(brand);
    }
}
