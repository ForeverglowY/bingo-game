package com.fc.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.commonutils.JwtUtils;
import com.fc.commonutils.MD5;
import com.fc.management.entity.User;
import com.fc.management.entity.vo.RegisterVo;
import com.fc.management.mapper.UserMapper;
import com.fc.management.service.UserService;
import com.fc.servicebase.exceptionhandler.BingoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 *
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Override
    public String login(User user) {
        //获取登录手机号和密码
        String phone = user.getPhone();
        String inputPassword = user.getPassword();
        String password;
        //手机号和密码非空判断
        if (StringUtils.isEmpty(phone) || StringUtils.isEmpty(inputPassword)) {
            throw new BingoException(20001, "登录失败");
        }
        password = MD5.encrypt(inputPassword);

        //判断手机号是否正确
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);

        User userByphone = userMapper.selectOne(queryWrapper);
        if (userByphone == null) {
            throw new BingoException(20001, "手机号不存在");
        }

        //判断密码
        if (!password.equals(userByphone.getPassword())) {
            throw new BingoException(20001, "密码错误");
        }

        //判断用户是否被禁用
        if (userByphone.getIsDisabled() == 1) {
            throw new BingoException(20001, "用户被禁用");
        }

        return JwtUtils.getJwtToken(userByphone.getId(), userByphone.getUsername());
    }

    @Override
    public String loginByCode(String phone, String code) {
        if (StringUtils.isEmpty(code) || StringUtils.isEmpty(phone)) {
            throw new BingoException(20001, "注册失败");
        }
        String codeInRedis = redisTemplate.opsForValue().get(phone);

        if (code.equals(codeInRedis)) {
            QueryWrapper<User> wrapper = new QueryWrapper<>();
            wrapper.eq("phone", phone);
            User user = userMapper.selectOne(wrapper);
            if (user == null) {
                return "notExist";
            }
            return JwtUtils.getJwtToken(user.getId(), user.getUsername());
        }
        return null;
    }

    @Override
    public int register(RegisterVo registerVo) {
        String username = registerVo.getUsername();
        String password = registerVo.getPassword();
        String avatar = registerVo.getAvatar();
        String identity = registerVo.getIdentity();
        String phone = registerVo.getPhone();
        String code = registerVo.getCode();

        //非空判断
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password) || StringUtils.isEmpty(identity)
                || StringUtils.isEmpty(phone) || StringUtils.isEmpty(code)) {
            throw new BingoException(20001, "注册失败");
        }

        String codeInRedis = redisTemplate.opsForValue().get(phone);

        if (!code.equals(codeInRedis)) {
            return -1;
        }
        redisTemplate.delete(phone);

        User user = new User();
        BeanUtils.copyProperties(registerVo, user);

        user.setPassword(MD5.encrypt(user.getPassword()));

        if (StringUtils.isEmpty(user.getAvatar())) {
            user.setAvatar("https://guli-file-everglow.oss-cn-beijing.aliyuncs.com/2022/07/04/f646d9077c05451cb417144a5ad7d3891609944681286.jpg");
        }

        return userMapper.insert(user);
    }
}




