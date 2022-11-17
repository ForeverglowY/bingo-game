package com.fc.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.commonutils.JwtUtils;
import com.fc.commonutils.MD5;
import com.fc.commonutils.R;
import com.fc.management.entity.Comment;
import com.fc.management.entity.User;
import com.fc.management.entity.vo.RegisterVo;
import com.fc.management.service.CommentService;
import com.fc.management.service.UserService;
import com.fc.servicebase.exceptionhandler.BingoException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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
    @Autowired
    private CommentService commentService;


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

    @GetMapping("/userInfo/{id}")
    public Map<String, String> getById(@PathVariable("id") String id) {
        //phone name
        User user = userService.getById(id);
        com.fc.commonutils.User user1 = new com.fc.commonutils.User();
        if (user == null) {
            throw new BingoException(20001, "没有查到相关记录");
        }
        Map<String, String> map = new HashMap<>();
        map.put("username", user.getUsername());
        map.put("phone", user.getPhone());
        return map;
    }

    /**
     * 添加用户
     * @param user 用户
     * @return R
     */
    @PostMapping("/save")
    public R save(@RequestBody User user) {
        user.setPassword(MD5.encrypt(user.getPassword()));
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
        user.setPassword(MD5.encrypt(user.getPassword()));
        boolean b = userService.updateById(user);
        return b ? R.ok().message("修改成功") : R.error().message("修改失败");
    }

    /**
     * 登录
     */
    @PostMapping("/login")
    public R login(@RequestBody User user) {
        //返回token
        String token = userService.login(user);
        return R.ok().data("token", token);
    }

    @PostMapping("/loginByCode")
    public R loginByCode(@RequestParam("phone") String phone,
                         @RequestParam("code") String code) {
        String token = userService.loginByCode(phone, code);
        if (StringUtils.isEmpty(token)) {
            return R.error().message("登录失败");
        }
        if ("notExist".equals(token)) {
            return R.error().data("token", "notExist").message("您还没有注册, 请注册!");
        }
        return R.ok().data("token", token);
    }

    @PostMapping("/register")
    public R register(@RequestBody RegisterVo registerVo) {
        int rows = userService.register(registerVo);
        if (rows == -1) {
            return R.error().message("验证码错误");
        }
        if (rows <= 0) {
            return R.error().message("注册失败");
        }
        return R.ok().message("注册成功");
    }

    /**
     * 根据 token 获取用户信息
     */
    @GetMapping("/userInfo")
    public R getMemberInfo(HttpServletRequest request) {
        // 调用 JWT 工具类的方法，根据 request 对象获取头信息，返回用户id
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        //查询数据库 获取用户信息
        User user = userService.getById(userId);
        return R.ok().data("userInfo", user);
    }

    @GetMapping("/userInfo/token")
    public R getUserInfo(@RequestParam String token) {
        String userId = JwtUtils.getMemberIdByJwtToken(token);
        User user = userService.getById(userId);
        return R.ok().data("userInfo", user);
    }

    /**
     * 点击发送验证码的时候，查看手机号是否已经被注册
     */
    @GetMapping("/check/{phone}")
    public R isThisMobileRegistered(@PathVariable("phone") String phone) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("phone", phone);
        int count = userService.count(queryWrapper);
        if (count != 0) {
            return R.error().data("phoneRepeat", true);
        }
        return R.ok();
    }

    /**
     * 查询该用户的评论
     */
    @GetMapping("/comment")
    public R getCommentList(HttpServletRequest request) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        String id = JwtUtils.getMemberIdByJwtToken(request);
        wrapper.eq("user_id", id);
        List<Comment> commentList = commentService.list(wrapper);
        return R.ok().data("commentList", commentList);
    }

}
