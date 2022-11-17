package com.fc.sms.controller;


import com.fc.commonutils.R;
import com.fc.sms.service.MsmService;
import com.fc.sms.utils.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author Everglow
 * Created at 2022/08/21 16:47
 */
@RestController
@RequestMapping("/msm")

public class MsmController {

    @Autowired
    private MsmService msmService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 发送验证码
     * @param phoneNumber 接收的手机号
     * @return
     */
    @GetMapping("/send/{phoneNumber}")
    public R sendMsm(@PathVariable("phoneNumber") String phoneNumber) {
        //从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phoneNumber);
        if (!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //如果redis取不到，通过阿里云发送
        // 生成随机值，传递给阿里云并进行发送
        code = RandomUtil.getSixBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        //调用 service 发送短信的方法
        boolean isSend = msmService.sendMsg(param, phoneNumber);
        if (!isSend) return R.error().message("发送短信验证码失败");
        //发送成功，把发送成功的验证码放到redis中，设置有效时间
        redisTemplate.opsForValue().set(phoneNumber, code, 5, TimeUnit.MINUTES);
        return R.ok();
    }

    @GetMapping("/sendPro/{phoneNumber}")
    public R sendMsmPro(@PathVariable("phoneNumber") String phoneNumber) {
        //从redis获取验证码，如果获取到直接返回
        String code = redisTemplate.opsForValue().get(phoneNumber);
        if (!StringUtils.isEmpty(code)) {
            return R.ok();
        }
        //如果redis取不到，通过阿里云发送
        // 生成随机值，传递给阿里云并进行发送
        code = RandomUtil.getSixBitRandom();
        Map<String, Object> param = new HashMap<>();
        param.put("code", code);
        //调用 service 发送短信的方法
        boolean isSend = false;
        try {
            isSend = msmService.sendMsgPro(param, phoneNumber);
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (!isSend) return R.error().message("发送短信验证码失败");
        //发送成功，把发送成功的验证码放到redis中，设置有效时间
        redisTemplate.opsForValue().set(phoneNumber, code, 5, TimeUnit.MINUTES);
        return R.ok();
    }
}
