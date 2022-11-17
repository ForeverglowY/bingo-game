package com.fc.order.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author Everglow
 * Created at 2022/11/10 15:07
 */
@Data
public class OrderQuery implements Serializable {

    private String username;
    private Date begin;
    private Date end;
}
