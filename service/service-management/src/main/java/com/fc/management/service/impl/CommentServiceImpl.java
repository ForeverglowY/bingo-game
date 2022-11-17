package com.fc.management.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fc.management.entity.Comment;
import com.fc.management.entity.User;
import com.fc.management.service.CommentService;
import com.fc.management.mapper.CommentMapper;
import com.fc.management.service.GameService;
import com.fc.management.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

/**
 *
 */
@Service
public class CommentServiceImpl extends ServiceImpl<CommentMapper, Comment>
    implements CommentService{

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private GameService gameService;

    @Override
    public List<Comment> childrenList(String id) {
        QueryWrapper<Comment> wrapper = new QueryWrapper<>();
        wrapper.eq(!StringUtils.isEmpty(id), "target_id", id);
        wrapper.orderByAsc("gmt_modified");
        List<Comment> childrenList = commentMapper.selectList(wrapper);
        for (Comment comment : childrenList) {
            String commentId = comment.getId();
            comment.setGameName(gameService.getById(comment.getGameId()).getName());
            User user = userService.getById(comment.getUserId());
            comment.setUsername(user.getUsername());
            comment.setAvatar(user.getAvatar());
            if (commentMapper.hasChildren(commentId) > 0) {
                List<Comment> commentList = childrenList(commentId);
                comment.setChildren(commentList);
            }
        }
        return childrenList;
    }

}




