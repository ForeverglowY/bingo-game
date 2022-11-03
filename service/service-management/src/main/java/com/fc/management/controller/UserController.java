package com.fc.management.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.commonutils.R;
import com.fc.management.entity.User;
import com.fc.management.service.UserService;
import com.fc.servicebase.exceptionhandler.BingoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author ZhangChen        Email:zhangchen_savior@163.com
 * @version 1.0
 * @Description 描述
 * @date 2022/11/3 9:16
 */
@RestController
@RequestMapping("/management/user")
public class UserController {
    @Autowired
    private UserService userService;


    /**
     *
     * @param current 当前页码
     * @param limit   每页条数
     * @return 查询结果 类型 list
     */
    @GetMapping("/list/{current}/{limit}")
    public R list(@PathVariable("current") Long current,
                  @PathVariable("limit") Long limit) {
        //创建 Page 对象
        Page<User> page = new Page<>(current, limit);
        userService.page(page, null);
        long total = page.getTotal();
        List<User> records = page.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);

        return R.ok().data(map);
    }

    /**
     * 根据 id 查询 user
     * @param id id
     * @return R
     */
    @GetMapping("/{id}")
    public R getUserById(@PathVariable("id") String id) {
        User user = userService.getById(id);
        if (user == null) {
            throw new BingoException(20001, "没有查到相关记录");
        }
        return R.ok().data("user", user);
    }

    /**
     * 添加用户
     * @param user 用户
     * @return R
     */
    @PostMapping("/save")
    public R save(@RequestBody User user) {
        boolean save = userService.save(user);
        return save ? R.ok().message("添加成功") : R.error().message("添加失败");
    }

    /**
     * 根据 id 删除 user
     * @param id id
     * @return R
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") String id) {
        boolean b = userService.removeById(id);
        return b ? R.ok().message("删除成功") : R.error().message("删除失败");
    }

    /**
     * 修改 user
     * @param user 内容
     * @return R
     */
    @PutMapping("/update")
    public R update(@RequestBody User user) {
        boolean b = userService.updateById(user);
        return b ? R.ok().message("修改成功") : R.error().message("修改失败");
    }
}
