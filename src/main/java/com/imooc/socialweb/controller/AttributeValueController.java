package com.imooc.socialweb.controller;


import com.imooc.socialweb.base.JsonReturnType;
import com.imooc.socialweb.pojo.AttributeValue;
import com.imooc.socialweb.service.AttributeValueService;
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
@RequestMapping("/attribute-value")
public class AttributeValueController {
    @Autowired
    private AttributeValueService attributeValueService;

    @RequestMapping(value = "create", method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType create(@RequestBody AttributeValue attributeValue) {
        attributeValueService.save(attributeValue);
        return JsonReturnType.createType(attributeValue);
    }
}
