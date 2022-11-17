package com.fc.management.entity.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author Everglow
 * Created at 2022/11/10 16:02
 */
@Data
public class RegisterVo {
    @ApiModelProperty(value = "手机号")
    private String phone;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "昵称")
    private String username;

    @ApiModelProperty(value = "验证码")
    private String code;

    @ApiModelProperty(value = "头像")
    private String avatar;

    @ApiModelProperty(value = "身份证")
    private String identity;

}
