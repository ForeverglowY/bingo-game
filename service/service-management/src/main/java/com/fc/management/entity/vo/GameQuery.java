package com.fc.management.entity.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @author Everglow
 * Created at 2022/11/04 15:23
 */
@Data
public class GameQuery implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private String typeId;
    private BigDecimal score;
    private BigDecimal minPrice;
    private BigDecimal maxPrice;
    private Date begin;
    private Date end;
}
