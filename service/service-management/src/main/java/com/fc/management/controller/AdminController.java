package com.fc.management.controller;

import com.fc.commonutils.JwtUtils;
import com.fc.commonutils.R;
import com.fc.management.entity.Admin;
import com.fc.management.entity.vo.AdminVo;
import com.fc.management.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public R login(@RequestBody AdminVo adminVo) {
        String token = adminService.login(adminVo.getUsername(), adminVo.getPassword());
        if (token == null) {
            return R.error().message("用户不存在");
        }
        return R.ok().data("token", token);
    }

    /**
     * info
     */
    @GetMapping("/info")
    public R info(@RequestParam String token) {
        String id = JwtUtils.getMemberIdByJwtToken(token);
        String username = adminService.getById(id).getUsername();
        return R.ok().data("roles", "[admin]")
                .data("name", username)
                .data("avatar", "https://wpimg.wallstcn.com/f778738c-e4f8-4870-b634-56703b4acafe.gif");
    }

    @PostMapping("/logout")
    public R logout() {
        return R.ok();
    }

    @PostMapping("/register")
    public R register(@RequestBody Admin admin) {
        adminService.save(admin);
        return R.ok();
    }

}
