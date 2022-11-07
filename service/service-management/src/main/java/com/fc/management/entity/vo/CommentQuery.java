package com.fc.management.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Everglow
 * Created at 2022/11/07 16:51
 */
@Data
public class CommentQuery implements Serializable {
    private static final long serialVersionUID = 1L;

    private String gameId;

    private String username;

    private Date begin;

    private Date end;
}
