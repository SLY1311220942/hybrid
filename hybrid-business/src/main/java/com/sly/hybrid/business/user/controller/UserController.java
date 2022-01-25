package com.sly.hybrid.business.user.controller;

import com.sly.hybrid.business.user.model.vo.UserDetailVo;
import com.sly.hybrid.business.user.param.UserDetailParam;
import com.sly.hybrid.business.user.service.UserService;
import com.sly.myplugin.base.result.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户 controller
 *
 * @author SLY
 * @date 2022/1/25
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 查询用户详情
     *
     * @param param 用户详情参数
     * @return {@link Result}<{@link UserDetailVo}>
     * @author SLY
     * @date 2022/1/25
     */
    @RequestMapping("/findUserDetail")
    public Result<UserDetailVo> findUserDetail(@Validated UserDetailParam param) {
        return userService.findUserDetail(param);
    }
}
