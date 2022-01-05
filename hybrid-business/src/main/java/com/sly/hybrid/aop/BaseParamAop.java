package com.sly.hybrid.aop;

import com.sly.hybrid.common.token.TokenUtil;
import com.sly.hybrid.login.UserToken;
import com.sly.hybrid.param.BaseParam;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 基础参数切面
 *
 * @author SLY
 * @date 2021/12/31
 */
@Aspect
@Component
public class BaseParamAop {

    @Autowired
    private TokenUtil tokenUtil;

    @Around("@annotation(org.springframework.web.bind.annotation.RequestMapping) " +
            "|| @annotation(org.springframework.web.bind.annotation.GetMapping)" +
            "|| @annotation(org.springframework.web.bind.annotation.PostMapping)")
    public Object doAround(ProceedingJoinPoint point) throws Throwable {
        int i = 0;
        UserToken userToken = tokenUtil.getUserToken();
        Object[] args = point.getArgs();
        for (Object arg : args) {
            if (arg instanceof BaseParam) {
                ((BaseParam) arg).set_user(userToken);
            }
        }
        return point.proceed();
    }
}
