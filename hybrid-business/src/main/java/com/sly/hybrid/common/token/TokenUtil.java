package com.sly.hybrid.common.token;

import cn.hutool.core.util.StrUtil;
import com.sly.hybrid.common.redis.RedisHelper;
import com.sly.hybrid.constant.LoginConst;
import com.sly.hybrid.login.UserToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.time.Duration;

/**
 * token工具类
 *
 * @author SLY
 * @date 2021/12/30
 */
@Component
public class TokenUtil {
    /** 登录过期时间 */
    private static final Duration LOGIN_EXPIRE = Duration.ofDays(7);


    @Autowired
    private RedisHelper redisHelper;

    /**
     * 获取用户token
     *
     * @return {@link UserToken}
     * @author SLY
     * @date 2021/12/30
     */
    public UserToken getUserToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String token = request.getHeader(LoginConst.USER_TOKEN_HEADER);
        return redisHelper.get(token, UserToken.class);
    }

    /**
     * 获取请求token
     *
     * @return {@link String}
     * @author SLY
     * @date 2021/12/30
     */
    public String getToken() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        return request.getHeader(LoginConst.USER_TOKEN_HEADER);
    }

    /**
     * token是否存在
     *
     * @param token token
     * @return boolean
     * @author SLY
     * @date 2021/12/30
     */
    public boolean existsToken(String token) {
        String key = LoginConst.USER_TOKEN_HEADER + token;
        return redisHelper.exists(key);
    }

    /**
     * 刷新token过期时间
     *
     * @param token token
     * @author SLY
     * @date 2021/12/30
     */
    public void refreshUserToken(String token) {
        String key = LoginConst.USER_TOKEN_HEADER + token;
        redisHelper.setExpire(key, LOGIN_EXPIRE);
    }
}
