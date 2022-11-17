package com.fc.order.service.impl;

import cn.hutool.core.util.RandomUtil;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.commonutils.Game;
import com.fc.order.client.ManagementClient;
import com.fc.order.entity.Order;
import com.fc.order.service.OrderService;
import com.fc.order.mapper.OrderMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 *
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order>
    implements OrderService{

    @Autowired
    private ManagementClient managementClient;
    @Autowired
    private OrderMapper orderMapper;

    @Override
    public String createOrders(String gameId, String userId) {
        Map<String, String> userMap = managementClient.getById(userId);
        Map<String, Object> gameMap = managementClient.getInfoGameId(gameId);
        Order order = new Order();
        String price = gameMap.get("price") + "";
        BigDecimal bigDecimal = new BigDecimal(price);
        order.setGameId(gameId);
        order.setGamePicture((String) gameMap.get("picture"));
        order.setOrderNo(RandomUtil.randomNumbers(10));
        order.setPhone(userMap.get("phone"));
        order.setTotalFee(bigDecimal);
        order.setUserId(userId);
        order.setUsername(userMap.get("username"));
        order.setStatus(false);
        orderMapper.insert(order);
        return order.getOrderNo();
    }

    @Override
    public List<Game> getGameList(String userId) {
        return orderMapper.getGameList(userId);
    }

}




