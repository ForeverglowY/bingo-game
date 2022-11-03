package com.fc.management.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fc.commonutils.R;
import com.fc.management.entity.Comment;
import com.fc.management.service.CommentService;
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
@RequestMapping("/management/comment")
public class CommentController {

    @Autowired
    private CommentService commentService ;

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
        Page<Comment> page = new Page<>(current, limit);
        commentService.page(page, null);
        long total = page.getTotal();
        List<Comment> records = page.getRecords();
        Map<String, Object> map = new HashMap<>();
        map.put("total", total);
        map.put("rows", records);

        return R.ok().data(map);
    }

    /**
     * 根据 id 查询 comment
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
     * @param comment 评论对象
     * @return R
     */
    @PostMapping("/save")
    public R save(@RequestBody Comment comment) {
        boolean save = commentService.save(comment);
        return save ? R.ok().message("添加成功") : R.error().message("添加失败");
    }

    /**
     * 根据 id 删除 comment
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
     * @param comment 评论
     * @return R
     */
    @PutMapping("/update")
    public R update(@RequestBody Comment comment) {
        boolean b = commentService.updateById(comment);
        return b ? R.ok().message("修改成功") : R.error().message("修改失败");
    }
}
