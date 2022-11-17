package com.fc.order.controller.front;

import com.fc.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Everglow
 * Created at 2022/11/14 16:29
 */
@RestController
@RequestMapping("/order/front")
public class OrderFrontController {
    @Autowired
    private OrderService orderService;


}
