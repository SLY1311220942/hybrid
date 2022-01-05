package com.sly.hybrid.business.login.controller;

import com.sly.hybrid.annotation.IgnoreToken;
import com.sly.hybrid.business.login.param.SignInParam;
import com.sly.hybrid.business.login.service.LoginService;
import com.sly.hybrid.common.token.TokenUtil;
import com.sly.myplugin.base.result.Result;
import com.sly.myplugin.preventrepeat.annotation.PreventRepeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 登录 controller
 *
 * @author SLY
 * @date 2022/1/2
 */
@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private LoginService loginService;

    @Autowired
    private TokenUtil tokenUtil;

    /**
     * 登录
     *
     * @param param 登录参数 {@link SignInParam}
     * @return {@link Result}
     * @author SLY
     * @date 2022/1/2
     */
    @IgnoreToken
    @PreventRepeat
    @PostMapping("/signIn")
    public Result<?> signIn(@Validated SignInParam param) {
        return loginService.signIn(param);
    }

    /**
     * 登出
     *
     * @return {@link Result}
     * @author SLY
     * @date 2022/1/2
     */
    @IgnoreToken
    @PostMapping("/signOut")
    public Result<?> signOut() {
        return loginService.signOut(tokenUtil.getToken());
    }
}
