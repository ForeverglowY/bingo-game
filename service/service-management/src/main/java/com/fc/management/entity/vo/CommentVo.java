package com.fc.management.entity.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.fc.management.entity.Comment;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author Everglow
 * Created at 2022/11/07 16:07
 */
@Data
public class CommentVo implements Serializable {
    /**
     * 用户id
     */
    private String username;

    /**
     * 用户头像
     */
    private String avatar;


    /**
     * 评论内容
     */
    private String content;

    /**
     * 回复他的评论
     */
    private List<CommentVo> children;
}
