package com.sly.hybrid.common.exception.handler;

import com.sly.myplugin.base.exception.BusinessException;
import com.sly.myplugin.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * 业务异常处理器
 * @author SLY
 * @date 2022/1/8
 */
@Slf4j
@Order(100000)
@ResponseBody
@ControllerAdvice
public class BusinessExceptionHandler {

    /**
     * 业务异常处理器
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
}
