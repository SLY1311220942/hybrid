package com.sly.hybrid.business.login.param;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * 注册参数
 *
 * @author SLY
 * @date 2022/1/2
 */
@Setter
@Getter
public class RegisterParam implements Serializable {
    /** 账号 */
    @NotBlank(message = "登录账号不能为空！")
    private String account;
    /** 密码 */
    @NotBlank(message = "密码不能为空！")
    private String password;
    /** 用户名 */
    @NotBlank(message = "用户名不能为空！")
    @Size(message = "用户名为1-20个字符", max = 20)
    private String username;
}
