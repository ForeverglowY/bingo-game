package com.fc.management.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.management.entity.User;
import com.fc.management.entity.vo.RegisterVo;


/**
 *
 */
public interface UserService extends IService<User> {

    String login(User user);

    String loginByCode(String phone, String code);

    int register(RegisterVo registerVo);
}
