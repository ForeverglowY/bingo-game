package com.fc.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.commonutils.JwtUtils;
import com.fc.commonutils.MD5;
import com.fc.management.entity.Admin;
import com.fc.management.entity.vo.AdminVo;
import com.fc.management.service.AdminService;
import com.fc.management.mapper.AdminMapper;
import com.fc.servicebase.exceptionhandler.BingoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin>
    implements AdminService{

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public String login(String username, String password) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", username);
        wrapper.eq("password", MD5.encrypt(password));
        if (StringUtils.isEmpty(username) || StringUtils.isEmpty(password)) {
            throw new BingoException(20001, "用户名和密码不能为空");
        }
        Admin admin = adminMapper.selectOne(wrapper);
        if (admin == null) {
            return null;
        }
        return JwtUtils.getJwtToken(admin.getId(), admin.getUsername());
    }


}




