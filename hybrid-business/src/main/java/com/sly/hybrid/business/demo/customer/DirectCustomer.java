package com.sly.hybrid.business.demo.customer;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import com.sly.hybrid.business.demo.model.Order;
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
        System.out.println("消费端Payload: " + JSON.toJSONString(order));
        JSON.parseObject("", Object.class);
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        // 手工ACK
        channel.basicAck(deliveryTag, false);
    }
}
