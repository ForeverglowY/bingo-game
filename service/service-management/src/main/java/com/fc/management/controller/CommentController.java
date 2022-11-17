package com.fc.management.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.commonutils.JwtUtils;
import com.fc.commonutils.R;
import com.fc.management.entity.Comment;
import com.fc.management.entity.User;
import com.fc.management.entity.vo.CommentQuery;
import com.fc.management.service.CommentService;
import com.fc.management.service.GameService;
import com.fc.management.service.UserService;
import com.fc.servicebase.exceptionhandler.BingoException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author ZhangChen        Email:zhangchen_savior@163.com
 * @version 1.0
 * @Description 描述
 * @date 2022/11/3 9:16
 */
@RestController
@RequestMapping("/management/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    /**
     * @param current 当前页码
     * @param limit   每页条数
     * @return 查询结果 类型 list
     */
    @PostMapping("/list/{current}/{limit}")
    public R list(@PathVariable("current") Long current,
                  @PathVariable("limit") Long limit,
                  @RequestBody(required = false) CommentQuery commentQuery) {
        //创建 Page 对象
        Page<Comment> page = new Page<>(current, limit);
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        String gameId;
        String username;
        Date begin;
        Date end;
        if (commentQuery != null) {
            gameId = commentQuery.getGameId();
            username = commentQuery.getUsername();
            begin = commentQuery.getBegin();
            end = commentQuery.getEnd();
            if (!StringUtils.isEmpty(gameId)) {
                wrapper.eq("game_id", gameId);
            }
            if (!StringUtils.isEmpty(username)) {
                wrapper.eq("username", username);
            }
            if (!StringUtils.isEmpty(begin)) {
                wrapper.gt("gmt_create", begin);
            }
            if (!StringUtils.isEmpty(end)) {
                wrapper.lt("gmt_create", end);
            }
        }
        wrapper.eq("target_id", "");
        wrapper.orderByAsc("gmt_modified");
        commentService.page(page, wrapper);
        long total = page.getTotal();
        List<Comment> records = page.getRecords();
        if (records == null) {
            return R.ok().message("评论列表为空");
        }
        for (Comment record : records) {
            record.setGameName(gameService.getById(record.getGameId()).getName());
            User user = userService.getById(record.getUserId());
            record.setUsername(user.getUsername());
            record.setAvatar(user.getAvatar());
            List<Comment> comments = commentService.childrenList(record.getId());
            record.setChildren(comments);
        }

        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);

        return R.ok().data(map);
    }

    @GetMapping("/comment/{gameId}")
    public R getCommentListByGameId(@PathVariable("gameId") String gameId) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq("game_id", gameId);
        wrapper.eq("target_id", "");
        wrapper.orderByAsc("gmt_modified");
        List<Comment> list = commentService.list(wrapper);
        if (list == null) {
            return R.ok().message("评论列表为空");
        }
        for (Comment record : list) {
            record.setGameName(gameService.getById(record.getGameId()).getName());
            User user = userService.getById(record.getUserId());
            record.setUsername(user.getUsername());
            record.setAvatar(user.getAvatar());
            List<Comment> comments = commentService.childrenList(record.getId());
            record.setChildren(comments);
        }
        return R.ok().data("commentList", list);
    }

    /**
     * 根据 id 查询 comment
     *
     * @param id id
     * @return R
     */
    @GetMapping("/{id}")
    public R getCommentById(@PathVariable("id") String id) {
        Comment comment = commentService.getById(id);
        if (comment == null) {
            throw new BingoException(20001, "没有查到相关记录");
        }
        return R.ok().data("comment", comment);
    }

    /**
     * 添加评论
     *
     * @param comment 评论对象
     * @return R
     */
    @PostMapping("/save")
    public R save(@RequestBody Comment comment, HttpServletRequest request) {
        String userId = JwtUtils.getMemberIdByJwtToken(request);
        comment.setUserId(userId);
        boolean save = commentService.save(comment);
        return save ? R.ok().message("添加成功") : R.error().message("添加失败");
    }

    /**
     * 根据 id 删除 comment
     *
     * @param id id
     * @return R
     */
    @DeleteMapping("/{id}")
    public R delete(@PathVariable("id") String id) {
        boolean b = commentService.removeById(id);
        return b ? R.ok().message("删除成功") : R.error().message("删除失败");
    }

    /**
     * 修改 comment
     *
     * @param comment 评论
     * @return R
     */
    @PutMapping("/update")
    public R update(@RequestBody Comment comment) {
        boolean b = commentService.updateById(comment);
        return b ? R.ok().message("修改成功") : R.error().message("修改失败");
    }


}
