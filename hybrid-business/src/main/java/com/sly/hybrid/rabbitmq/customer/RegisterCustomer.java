package com.sly.hybrid.rabbitmq.customer;

import com.rabbitmq.client.Channel;
import com.sly.hybrid.business.user.model.po.User;
import com.sly.hybrid.constant.RabbitmqConst;
import com.sly.myplugin.email.model.MailInfo;
import com.sly.myplugin.email.sender.EmailSender;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 注册消费者
 *
 * @author SLY
 * @date 2022/1/4
 */
@Component
public class RegisterCustomer {

    @Autowired
    private EmailSender emailSender;

    /** 注册后发送激活邮件 */
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "register-queue", durable = "false"),
            key = "register",
            exchange = @Exchange(value = RabbitmqConst.AMQ_DIRECT, durable = "true", type = "direct", ignoreDeclarationExceptions = "true")))
    @Transactional(rollbackFor = Exception.class)
    public void onRegister(Message<User> message, Channel channel) throws Exception {
        User user = message.getPayload();
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        // 发送激活邮件
        MailInfo mailInfo = new MailInfo();
        mailInfo.setContent("<h1>sendHtmlEmail测试内容</h1>");
        mailInfo.setSubject("hybrid激活邮件");
        mailInfo.addAddressee(user.getAccount());
        emailSender.sendHtmlEmail(mailInfo);

        // 手工ACK
        if (deliveryTag != null) {
            channel.basicAck(deliveryTag, false);
        }
    }
}
