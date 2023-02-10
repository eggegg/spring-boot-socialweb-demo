package com.imooc.socialweb.controller;


import com.imooc.socialweb.base.JsonReturnType;
import com.imooc.socialweb.pojo.Test;
import com.imooc.socialweb.service.TestService;
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
@RequestMapping("/test")
public class TestController {

    @Autowired
    private TestService testService;

    @RequestMapping(value = "/get",method = {RequestMethod.GET})
    @ResponseBody
    public JsonReturnType get(@RequestParam(name = "id") Long id){
        Test test = testService.getById(id);
        if (test != null) {
            return JsonReturnType.createType(test);
        } else {
            return JsonReturnType.createErrorType("test不存在");
        }
    }
}
