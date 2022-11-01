package com.fc.servicebase.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * @author Everglow
 * Created at 2022/06/25 0:27
 */

@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BingoException extends RuntimeException {

    private Integer code; //状态码

    private String msg; //异常信息
}
