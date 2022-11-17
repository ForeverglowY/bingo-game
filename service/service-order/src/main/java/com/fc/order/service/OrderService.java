package com.fc.order.service;

import com.fc.commonutils.Game;
import com.fc.order.entity.Order;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface OrderService extends IService<Order> {

    public String createOrders(String gameId, String userId);

    List<Game> getGameList(String userId);
}
