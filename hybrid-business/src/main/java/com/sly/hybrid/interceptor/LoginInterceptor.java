package com.sly.hybrid.interceptor;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONUtil;
import com.sly.hybrid.annotation.IgnoreToken;
import com.sly.hybrid.common.token.TokenUtil;
import com.sly.hybrid.login.UserToken;
import com.sly.hybrid.param.BaseParam;
import com.sly.hybrid.result.ResultCode;
import com.sly.myplugin.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * 登录拦截器
 *
 * @author SLY
 * @date 2021/12/30
 */
@Slf4j
@Component
public class LoginInterceptor implements HandlerInterceptor {

    @Autowired
    private TokenUtil tokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 资源文件直接跳过
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        response.setCharacterEncoding("UTF-8");
        HandlerMethod handlerMethod = (HandlerMethod) handler;

        // 标记跳过的
        Method method = handlerMethod.getMethod();
        if (method.isAnnotationPresent(IgnoreToken.class)) {
            return true;
        }

        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String requestPath = uri.replaceFirst(contextPath, "").replaceAll("/+", "/");

        log.info("请求路径:" + requestPath);


        String token = tokenUtil.getToken();
        // 没传token
        if (StrUtil.isEmpty(token)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONUtil.toJsonStr(Result.failed(ResultCode.LOGIN_TOKEN_NULL)));
            return false;
        }
        // 未命中登录信息
        if (!tokenUtil.existsToken(token)) {
            response.setContentType("application/json;charset=utf-8");
            response.getWriter().write(JSONUtil.toJsonStr(Result.failed(ResultCode.LOGIN_TOKEN_MISS)));
            return false;
        }
        // 刷新token过期时间
        tokenUtil.refreshUserToken(token);
        // 登录
        UserToken userToken = tokenUtil.getUserToken();

        request.setAttribute("_user", userToken);

        return true;
    }
}
