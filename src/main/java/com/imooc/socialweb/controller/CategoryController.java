package com.imooc.socialweb.controller;


import com.imooc.socialweb.base.JsonReturnType;
import com.imooc.socialweb.pojo.Category;
import com.imooc.socialweb.service.CategoryService;
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
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "create", method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType create(@RequestBody Category category) {
        categoryService.save(category);
        return JsonReturnType.createType(category);
    }
}
