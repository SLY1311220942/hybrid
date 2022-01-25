package com.sly.hybrid.business.user.service;

import cn.hutool.core.bean.BeanUtil;
import com.sly.hybrid.business.user.mapper.UserMapper;
import com.sly.hybrid.business.user.model.po.User;
import com.sly.hybrid.business.user.model.vo.UserDetailVo;
import com.sly.hybrid.business.user.param.UserDetailParam;
import com.sly.hybrid.business.user.param.UserUpdateParam;
import com.sly.myplugin.base.result.Result;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

/**
 * 用户 service
 *
 * @author SLY
 * @date 2022/1/25
 */
@Service
public class UserService {

    @Resource
    private UserMapper userMapper;

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
        UserDetailVo userDetailVo = new UserDetailVo();
        User user = userMapper.findById(param.get_user().getId());
        BeanUtil.copyProperties(user, userDetailVo);
        return Result.success(userDetailVo);
    }

    /**
     * 修改用户资料
     *
     * @param param 用户资料修改参数
     * @return {@link Result}
     * @author SLY
     * @date 2022/1/25
     */
    @RequestMapping("/updateUser")
    @Transactional(rollbackFor = Exception.class)
    public Result<?> updateUser(@Validated UserUpdateParam param) {
        User user = new User();
        BeanUtil.copyProperties(param, user);
        userMapper.updateUserInfo(user);
        return Result.success();
    }
}
