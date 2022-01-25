package com.sly.hybrid.business.login.controller;

import com.sly.hybrid.annotation.IgnoreToken;
import com.sly.hybrid.business.login.param.ActiveParam;
import com.sly.hybrid.business.login.param.RegisterParam;
import com.sly.hybrid.business.login.service.RegisterService;
import com.sly.myplugin.base.result.Result;
import com.sly.myplugin.preventrepeat.annotation.PreventRepeat;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 注册 controller
 *
 * @author SLY
 * @date 2022/1/2
 */
@RestController
@RequestMapping("/register")
public class RegisterController {

    @Autowired
    private RegisterService registerService;

    /**
     * 注册
     *
     * @param param 注册参数 {@link RegisterParam}
     * @return {@link Result}
     * @author SLY
     * @date 2022/1/2
     */
    @IgnoreToken
    @PreventRepeat
    @PostMapping("/register")
    public Result<?> register(@Validated RegisterParam param) {
        return registerService.register(param);
    }

    /**
     * 激活
     *
     * @param param 激活参数 {@link ActiveParam}
     * @return {@link Result}
     * @author SLY
     * @date 2022/1/6
     */
    @IgnoreToken
    @PreventRepeat
    @RequestMapping("/active")
    public Result<?> active(@Validated ActiveParam param) {
        return registerService.active(param);
    }
}
