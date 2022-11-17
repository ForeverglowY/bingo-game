package com.fc.order.service;

import com.fc.order.entity.PayLog;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
 *
 */
public interface PayLogService extends IService<PayLog> {

    Map<String, Object> createNative(String orderNo);

    Map<String, String> queryPayStatus(String orderNo);

    void updateOrderStatus(Map<String, String> map);
}
