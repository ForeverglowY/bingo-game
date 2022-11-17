package com.fc.order.mapper;

import com.fc.commonutils.Game;
import com.fc.order.entity.Order;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * @Entity com.fc.order.entity.Order
 */
public interface OrderMapper extends BaseMapper<Order> {

    List<Game> getGameList(String userId);
}




