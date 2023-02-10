package com.imooc.socialweb.controller;


import com.imooc.socialweb.base.JsonReturnType;
import com.imooc.socialweb.pojo.Stock;
import com.imooc.socialweb.service.StockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
@RequestMapping("/stock")
public class StockController {

    @Autowired
    private StockService stockService;

    @RequestMapping(value = "create",method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType create(@RequestBody Stock stock) {
        stockService.increaseStock(stock.getSkuId(), stock.getShopId(), stock.getStockCount());
        return JsonReturnType.createType(stock);
    }

}
