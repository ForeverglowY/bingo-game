package com.fc.management.service;

import com.fc.management.entity.Comment;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 *
 */
public interface CommentService extends IService<Comment> {

    List<Comment> childrenList(String id);

}
