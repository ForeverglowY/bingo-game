package com.fc.management.controller;

import com.fc.commonutils.JwtUtils;
import com.fc.commonutils.MD5;
import com.fc.commonutils.R;
import com.fc.management.entity.Admin;
import com.fc.management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @author Everglow
 * Created at 2022/11/01 11:41
 */
@RestController
@RequestMapping("/management/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    @GetMapping("/login")
    public R login(String username, String password) {
        String token = adminService.login(username, password);
        return R.ok().data("adminToken", token);
    }

    @PostMapping("/register")
    public R register(String username, String password) {
        Admin admin = new Admin();
        admin.setUsername(username);
        admin.setPassword(MD5.encrypt(password));
        boolean save = adminService.save(admin);
        return save ? R.ok().message("注册成功!") : R.error().message("注册失败!");
    }

    @GetMapping("/info")
    public R info(HttpServletRequest request) {
        String id = JwtUtils.getMemberIdByJwtToken(request);
        Admin adminInfo = adminService.getById(id);
        return R.ok().data("adminInfo", adminInfo);
    }

}
