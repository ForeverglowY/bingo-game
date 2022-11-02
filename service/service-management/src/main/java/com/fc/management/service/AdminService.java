package com.fc.management.service;

import com.fc.management.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fc.management.entity.vo.AdminVo;

/**
 *
 */
public interface AdminService extends IService<Admin> {

    String login(String username, String password);
}
