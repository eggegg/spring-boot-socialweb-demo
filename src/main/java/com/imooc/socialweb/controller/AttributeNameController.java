package com.imooc.socialweb.controller;


import com.imooc.socialweb.base.JsonReturnType;
import com.imooc.socialweb.pojo.AttributeName;
import com.imooc.socialweb.service.AttributeNameService;
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
@RequestMapping("/attribute-name")
public class AttributeNameController {
    @Autowired
    private AttributeNameService attributeNameService;

    @RequestMapping(value = "create", method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType create(@RequestBody AttributeName attributeName) {
        attributeNameService.save(attributeName);
        return JsonReturnType.createType(attributeName);
    }
}
