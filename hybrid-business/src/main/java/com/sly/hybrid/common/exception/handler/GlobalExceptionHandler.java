package com.sly.hybrid.common.exception.handler;

import com.sly.myplugin.base.exception.BusinessException;
import com.sly.myplugin.base.result.BaseResultCode;
import com.sly.myplugin.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局异常处理器
 *
 * @author SLY
 * @date 2022/1/2
 */
@Slf4j
@ResponseBody
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 业务异常拦截器
     *
     * @param e       异常
     * @param request 请求
     * @return {@link Result}
     * @author SLY
     * @date 2022/1/2
     */
    @ExceptionHandler({BusinessException.class})
    public Result<?> handleBusinessException(BusinessException e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        return Result.failed(e.getCode(), e.getMsg(), e.getData());
    }

    /**
     * 异常拦截器
     *
     * @param e       异常
     * @param request 请求
     * @return {@link Result}
     * @author SLY
     * @date 2022/1/2
     */
    @ExceptionHandler({Exception.class})
    public Result<?> handleException(Exception e, HttpServletRequest request) {
        log.error(e.getMessage(), e);
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return Result.failed(BaseResultCode.HTTP_REQUEST_METHOD_NOT_SUPPORTED);
        }
        return Result.failed();
    }
}
