package com.sly.hybrid.param;

import com.sly.hybrid.login.UserToken;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * 基础参数
 *
 * @author SLY
 * @date 2021/12/30
 */
@Setter
@Getter
public class BaseParam implements Serializable {
    /** 登录用户信息 */
    private UserToken _user;
}
