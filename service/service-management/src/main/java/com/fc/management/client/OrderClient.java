package com.fc.management.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

/**
 * @author Everglow
 * Created at 2022/11/15 15:20
 */
@FeignClient("service-order")
@Component
public interface OrderClient {

    /**
     * 根据课程id和用户id查询订单表中订单状态
     */
    @GetMapping("/order/isBuyGame/{gameId}/{userId}")
    boolean isBuyGame(@PathVariable("gameId") String gameId, @PathVariable("userId") String userId);

    @DeleteMapping("/order/delete/{gameId}")
    public boolean deleteByGameId(@PathVariable("gameId") String gameId);
}
