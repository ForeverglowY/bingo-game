package com.fc.sms.service;

import java.util.Map;

/**
 * @author Everglow
 * Created at 2022/08/21 16:48
 */
public interface MsmService {


    /**
     * 发送短信
     */
    boolean sendMsg(Map<String, Object> param, String phoneNumber);

    public boolean sendMsgPro(Map<String, Object> param, String phoneNumber) throws Exception;

}
