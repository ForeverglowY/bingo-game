package com.fc.order.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.commonutils.Game;
import com.fc.commonutils.JwtUtils;
import com.fc.commonutils.R;
import com.fc.order.client.ManagementClient;
import com.fc.order.entity.Order;
import com.fc.order.entity.vo.OrderQuery;
import com.fc.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Everglow
 * Created at 2022/11/10 15:05
 */
@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ManagementClient managementClient;

    @PostMapping("/list/{current}/{limit}")
    public R list(@PathVariable("current") Long current,
                  @PathVariable("limit") Long limit,
                  @RequestBody(required = false) OrderQuery orderQuery) {
        Page<Order> page = new Page<>(current, limit);
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        String username;
        Date begin;
        Date end;
        if (orderQuery != null) {
            username = orderQuery.getUsername();
            begin = orderQuery.getBegin();
            end = orderQuery.getEnd();
            if (!StringUtils.isEmpty(username)) {
                wrapper.like("username", username);
            }
            if (!StringUtils.isEmpty(begin)) {
                wrapper.gt("gmt_modified", begin);
            }
            if (!StringUtils.isEmpty(end)) {
                wrapper.lt("gmt_modified", end);
            }
        }
        wrapper.orderByDesc("gmt_modified");
        orderService.page(page, wrapper);
        long total = page.getTotal();
        List<Order> records = page.getRecords();

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);

        return R.ok().data(map);
    }

    /**
     * 根据 id 删除 order
     *
     * @param id id
     * @return R
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") String id) {
        boolean b = orderService.removeById(id);
        return b ? R.ok().message("删除成功") : R.error().message("删除失败");
    }

    /**
     * 根据订单号 不是订单的id 查询订单信息
     */
    @GetMapping("/getOrderInfo/{orderNo}")
    public R getOrderInfo(@PathVariable("orderNo") String orderNo) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("order_no", orderNo);
        Order order = orderService.getOne(wrapper);
        Map<String, String> userMap = managementClient.getById(order.getUserId());
        Map<String, Object> gameMap = managementClient.getInfoGameId(order.getGameId());
        return R.ok().data("orderInfo", order).data("userMap", userMap).data("gameMap", gameMap);

    }

    /**
     * 生成订单
     */
    @PostMapping("/create/{gameId}")
    public R saveOrder(@PathVariable("gameId") String gameId, HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        //返回订单号
        String orderNo = orderService.createOrders(gameId, userId);
        return R.ok().data("orderNo", orderNo);
    }

    /**
     * 根据课程id和用户id查询订单表中订单状态
     */
    @GetMapping("/isBuyGame/{gameId}/{userId}")
    public boolean isBuyGame(@PathVariable("gameId") String gameId, @PathVariable("userId") String userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("game_id", gameId);
        queryWrapper.eq("user_id", userId);
        queryWrapper.eq("status", 1);
        int count = orderService.count(queryWrapper);
        return count > 0;
    }

    @GetMapping("/myGame")
    public R myGameList(HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        List<Game> gameList = orderService.getGameList(userId);
        return R.ok().data("gameList", gameList);
    }

    @DeleteMapping("/delete/{gameId}")
    public boolean deleteByGameId(@PathVariable("gameId") String gameId) {
        QueryWrapper<Order> wrapper = new QueryWrapper<>();
        wrapper.eq("game_id", gameId);
        return true;
    }
}
