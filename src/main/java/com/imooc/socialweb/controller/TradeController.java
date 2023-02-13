package com.imooc.socialweb.controller;


import com.imooc.socialweb.base.JsonReturnType;
import com.imooc.socialweb.pojo.Trade;
import com.imooc.socialweb.service.TradeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author socialeweb
 * @since 2023-02-13
 */
@RestController
@RequestMapping("/trade")
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @RequestMapping(value = "create",method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType create(@RequestBody Trade trade){
        try {
            trade = tradeService.createTrade(trade);
        }catch (Exception ex) {
            return JsonReturnType.createErrorType("下单失败");
        }

        if (trade == null){
            return JsonReturnType.createErrorType("下单失败");
        }
        return JsonReturnType.createType(trade);
    }

    @RequestMapping(value = "pay",method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType pay(@RequestBody Trade trade){
        trade = tradeService.pay(trade);
        if (trade == null){
            return JsonReturnType.createErrorType("支付失败");
        }
        return JsonReturnType.createType("Success");

    }

    @RequestMapping(value = "cancel",method = {RequestMethod.POST})
    @ResponseBody
    public JsonReturnType cancel(@RequestBody Trade trade){
        try{
            trade = tradeService.cancel(trade);
        }catch (Exception ex) {
            return JsonReturnType.createErrorType("取消失败");
        }
        if (trade == null){
            return JsonReturnType.createErrorType("取消失败");
        }
        return JsonReturnType.createType("Success");

    }
}
