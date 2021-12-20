package com.sly.hybrid.business.blog.controller;

import com.sly.hybrid.business.blog.param.PreventRepeatParam;
import com.sly.hybrid.business.blog.param.RedisDemoParam;
import com.sly.hybrid.business.blog.param.ValidateDemoParam;
import com.sly.hybrid.business.blog.service.DemoService;
import com.sly.myplugin.base.result.Result;
import com.sly.myplugin.preventrepeat.annotation.PreventRepeat;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * Demo controller
 *
 * @author SLY
 * @date 2021/12/3
 */
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private StringRedisTemplate redis01StringRedisTemplate;

    @Resource
    private StringRedisTemplate redis02StringRedisTemplate;

    @Resource
    private StringRedisTemplate redis03StringRedisTemplate;

    @Resource
    private DemoService demoService;

    /**
     * 参数验证 demo
     *
     * @param param ValidateDemo参数
     * @return {@link Result}
     * @author SLY
     * @date 2021/11/29
     */
    @RequestMapping("/validateDemo")
    public Result<?> validateDemo(@Validated ValidateDemoParam param) {
        return Result.success(param);
    }

    /**
     * redis demo
     *
     * @param param RedisDemo参数
     * @return {@link Result}
     * @author SLY
     * @date 2021/12/3
     */
    @RequestMapping("/redisDemo")
    public Result<?> redisDemo(@Validated RedisDemoParam param) {
        if (param.getRedisSource() == 1) {
            redis01StringRedisTemplate.opsForValue().set(param.getKey(), param.getValue());
        } else if (param.getRedisSource() == 2) {
            redis02StringRedisTemplate.opsForValue().set(param.getKey(), param.getValue());
        } else if (param.getRedisSource() == 3) {
            redis03StringRedisTemplate.opsForValue().set(param.getKey(), param.getValue());
        }
        return Result.success(param);
    }

    /**
     * 防重复提交 Demo
     *
     * @return {@link Result}
     * @author SLY
     * @date 2021/12/6
     */
    @PreventRepeat(5000)
    @RequestMapping("/preventRepeatDemo")
    public Result<?> preventRepeatDemo(PreventRepeatParam repeatParam) {
        return Result.success();
    }

    /**
     * 线程同步demo
     *
     * @return Result
     * @author SLY
     * @date 2021/12/7
     */
    @RequestMapping("/threadSyncDemo")
    public Result<?> threadSyncDemo() {
        demoService.syncDemo();
        return Result.success();
    }

    /**
     * 线程异步demo
     *
     * @return Result
     * @author SLY
     * @date 2021/12/7
     */
    @RequestMapping("/threadAsyncDemo")
    public Result<?> threadAsyncDemo() {
        demoService.asyncDemo();
        return Result.success();
    }
}
