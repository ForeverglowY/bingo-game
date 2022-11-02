package com.fc.management.controller;

import com.fc.commonutils.R;
import com.fc.management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Everglow
 * Created at 2022/11/01 11:41
 */
@RestController
@RequestMapping("/management/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;

    /**
     * login
     */
    @PostMapping("/login")
    public R login() {

        return R.ok().data("token", "admin");
    }

    /**
     * info
     */
    @GetMapping("/info")
    public R info() {
        return R.ok().data("roles", "[admin]")
                .data("name", "admin")
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }


}
