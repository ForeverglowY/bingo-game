package com.fc.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.commonutils.JwtUtils;
import com.fc.commonutils.MD5;
import com.fc.management.entity.Admin;
import com.fc.management.entity.vo.AdminVo;
import com.fc.management.service.AdminService;
import com.fc.management.mapper.AdminMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public String login(AdminVo adminVo) {
        QueryWrapper<Admin> wrapper = new QueryWrapper<>();
        wrapper.eq("username", adminVo.getUsername());
        wrapper.eq("password", MD5.encrypt(adminVo.getPassword()));
        Admin admin = adminMapper.selectOne(wrapper);
        return JwtUtils.getJwtToken(admin.getId(), admin.getUsername());
    }


}




