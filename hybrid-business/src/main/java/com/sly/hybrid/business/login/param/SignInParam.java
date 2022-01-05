package com.sly.hybrid.business.login.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录参数
 *
 * @author SLY
 * @date 2022/1/2
 */
@Setter
@Getter
public class SignInParam implements Serializable {
    /** 账号 */
    @NotBlank(message = "登录账号不能为空！")
    private String account;
    /** 密码 */
    @NotBlank(message = "登录密码不能为空！")
    private String password;
    /** 验证码 */
    private String verify;

}
