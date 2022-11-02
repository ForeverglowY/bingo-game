package com.fc.management.entity.vo;

import lombok.Data;

import java.io.Serializable;

/**
 * @author Everglow
 * Created at 2022/11/01 16:54
 */
@Data
public class AdminVo implements Serializable {

    private String username;
    private String password;
}
