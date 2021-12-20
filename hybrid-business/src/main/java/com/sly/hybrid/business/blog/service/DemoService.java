package com.sly.hybrid.business.blog.service;

import com.sly.myplugin.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author SLY
 * @date 2021/12/7
 */
@Slf4j
@Service
public class DemoService {

    /**
     * 同步
     *
     * @return {@link Result}
     * @author SLY
     * @date 2021/12/7
     */
    public Result<?> syncDemo() {
        try {
            Thread.sleep(10 * 1000);
            System.out.println("同步方法执行");
        } catch (Exception e) {
            log.error("同步方法异常", e);
        }
        return Result.success();
    }

    /**
     * 异步
     *
     * @return {@link Result}
     * @author SLY
     * @date 2021/12/7
     */
    @Async
    public Result<?> asyncDemo() {
        try {
            Thread.sleep(10 * 1000);
            System.out.println("异步方法执行");
        } catch (Exception e) {
            log.error("异步方法异常", e);
        }
        return Result.success();
    }
}
