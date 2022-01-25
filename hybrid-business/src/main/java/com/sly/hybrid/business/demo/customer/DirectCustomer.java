package com.sly.hybrid.business.demo.customer;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.sly.hybrid.business.demo.model.Order;
import com.sly.hybrid.rabbitmq.exchange.ExchangeConfig;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

/**
 * Direct 模式消费者
 *
 * @author SLY
 * @date 2021/12/28
 */
@Component
public class DirectCustomer {

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "order-queue", durable = "true"), key = "order",
            exchange = @Exchange(value = "amq.direct", durable = "true", type = "direct", ignoreDeclarationExceptions = "true")))
    @RabbitHandler
    public void onMessage(Message<Order> message, Channel channel) throws Exception {
        Order order = message.getPayload();
        System.out.println("消费端Payload: " + JSONUtil.toJsonStr(order));
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        // 手工ACK
        if (deliveryTag != null) {
            channel.basicAck(deliveryTag, false);
        }

    }

    @RabbitListener(bindings = @QueueBinding(value = @Queue(value = "order-queue", durable = "true"), key = "delay.key1",
            exchange = @Exchange(value = ExchangeConfig.LAZY_EXCHANGE, durable = "true", type = "x-delayed-message", ignoreDeclarationExceptions = "true")))
    @RabbitHandler
    public void onDelayMessage(Message<Order> message, Channel channel) throws Exception {
        Order order = message.getPayload();
        System.out.println("延时消费端Payload: " + JSONUtil.toJsonStr(order));
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        // 手工ACK
        if (deliveryTag != null) {
            channel.basicAck(deliveryTag, false);
        }

    }
}
