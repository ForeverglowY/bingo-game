package com.fc.order.controller;

/**
 * @author Everglow
 * Created at 2022/11/15 14:59
 */

import com.fc.commonutils.R;
import com.fc.order.service.PayLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * <p>
 * 支付日志表 前端控制器
 * </p>
 *
 * @author zwy
 * @since 2022-08-29
 */
@RestController
@RequestMapping("/order/paylog")

public class PayLogController {

    @Autowired
    private PayLogService payLogService;


    /**
     * 生成微信支付二维码接口
     */
    @GetMapping("/createNative/{orderNo}")
    public R createNative(@PathVariable("orderNo") String orderNo) {
        //返回相关信息，包括二维码地址还有其他信息
        Map<String, Object> map = payLogService.createNative(orderNo);
        return R.ok().data(map);
    }

    /**
     * 查询订单支付状态
     */
    @GetMapping("/queryPayStatus/{orderNo}")
    public R queryPayStatus(@PathVariable("orderNo") String orderNo) {
        Map<String, String> map = payLogService.queryPayStatus(orderNo);
        if (map == null) {
            return R.error().message("支付出错！");
        }
        if (map.get("trade_state").equals("SUCCESS")) {
            //支付成功 添加记录到pay_log表 并更新order表订单状态
            payLogService.updateOrderStatus(map);
            return R.ok().message("支付成功！");
        }

        return R.ok().code(25000).message("支付中...");
    }




}


