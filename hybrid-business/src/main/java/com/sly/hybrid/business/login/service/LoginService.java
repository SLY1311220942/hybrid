package com.sly.hybrid.business.login.service;

import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.MD5;
import com.sly.hybrid.business.login.param.SignInParam;
import com.sly.hybrid.business.user.mapper.UserMapper;
import com.sly.hybrid.business.user.model.po.User;
import com.sly.hybrid.common.redis.RedisHelper;
import com.sly.hybrid.constant.RedisConst;
import com.sly.hybrid.login.UserToken;
import com.sly.hybrid.result.ResultCode;
import com.sly.hybrid.util.DesUtil;
import com.sly.myplugin.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 登录 service
 *
 * @author SLY
 * @date 2022/1/2
 */
@Slf4j
@Service
public class LoginService {

    @Resource
    private UserMapper userMapper;
    @Resource
    private RedisHelper redisHelper;

    /**
     * 登录
     *
     * @param param 登录参数 {@link SignInParam}
     * @return {@link Result}
     * @author SLY
     * @date 2022/1/2
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<?> signIn(SignInParam param) {
        String account = DesUtil.decryptStr(param.getAccount());
        String password = DesUtil.decryptStr(param.getAccount());
        User user = userMapper.findUserByAccount(account);
        if (user == null) {
            return Result.failed(ResultCode.LOGIN_ACCOUNT_MISS);
        }
        String md5Password = MD5.create().digestHex(password);
        if (!StrUtil.equals(md5Password, user.getPassword())) {
            return Result.failed(ResultCode.LOGIN_PASSWORD_ERROR);
        }
        // 生成token
        String token = UUID.randomUUID().toString(true);
        UserToken userToken = new UserToken(user);
        userToken.setToken(token);
        // 保存token
        redisHelper.set(RedisConst.LOGIN_TOKEN_PREFIX + token, userToken);
        return Result.success(userToken);
    }

    /**
     * 登出
     *
     * @return {@link Result}
     * @author SLY
     * @date 2022/1/2
     */
    public Result<?> signOut(String token) {
        redisHelper.delete(RedisConst.LOGIN_TOKEN_PREFIX + token);
        return Result.success();
    }
}
