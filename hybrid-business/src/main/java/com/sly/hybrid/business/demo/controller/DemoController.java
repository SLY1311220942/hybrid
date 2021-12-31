package com.sly.hybrid.business.demo.controller;

import com.sly.hybrid.business.demo.model.Order;
import com.sly.hybrid.business.demo.param.PreventRepeatParam;
import com.sly.hybrid.business.demo.param.RedisDemoParam;
import com.sly.hybrid.business.demo.param.ValidateDemoParam;
import com.sly.hybrid.business.demo.service.DemoService;
import com.sly.myplugin.base.result.Result;
import com.sly.myplugin.preventrepeat.annotation.PreventRepeat;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.ReturnedMessage;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.UUID;

/**
 * Demo controller
 *
 * @author SLY
 * @date 2021/12/3
 */
@Slf4j
@RestController
@RequestMapping("/demo")
public class DemoController {

    @Resource
    private StringRedisTemplate redis01StringRedisTemplate;
    @Resource
    private RedisTemplate redis01RedisTemplate;

    @Resource
    private DemoService demoService;

    @Resource
    private RabbitTemplate rabbitTemplate;

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
            // redis02StringRedisTemplate.opsForValue().set(param.getKey(), param.getValue());
        } else if (param.getRedisSource() == 3) {
            // redis03StringRedisTemplate.opsForValue().set(param.getKey(), param.getValue());
        } else if (param.getRedisSource() == 4) {
            redis01RedisTemplate.opsForValue().set(param.getKey(), param);
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

    /**
     * 发送direct模式消息
     *
     * @return {@link Result}
     * @author SLY
     * @date 2021/12/28
     */
    @RequestMapping("/rabbitmqDirectQueue")
    public Result<?> rabbitmqDirectQueue() {
        Order order = new Order();
        rabbitTemplate.setConfirmCallback(confirmCallback);
        rabbitTemplate.setReturnsCallback(returnsCallback);
        String id = UUID.randomUUID().toString();
        CorrelationData correlationData = new CorrelationData(id);

        order.setId("1");
        order.setOrderSn("000001");

        rabbitTemplate.convertAndSend("amq.direct", "order", order, message -> {
            // 设置延迟，单位：毫秒值
            message.getMessageProperties().setHeader("delay", 20000);
            return message;
        }, correlationData);

        return Result.success();
    }

    /**
     * 回调函数: confirm确认
     */
    final RabbitTemplate.ConfirmCallback confirmCallback = (correlationData, ack, cause) -> {
        log.info("消息唯一标识：{}", correlationData);
        log.info("确认结果：{}", ack);
        if (!ack) {
            log.info("异常处理....");
            log.info("失败原因：{}", cause);
        }
    };

    /**
     * 回调函数: return返回
     * 启动消息失败返回，比如路由不到队列时触发回调
     */
    final RabbitTemplate.ReturnsCallback returnsCallback = returned -> {
        log.info("消息主体 message : " + returned.getMessage());
        log.info("消息主体 message : " + returned.getReplyCode());
        log.info("描述：" + returned.getReplyText());
        log.info("消息使用的交换器 exchange : " + returned.getExchange());
        log.info("消息使用的路由键 routing : " + returned.getRoutingKey());
    };
}
