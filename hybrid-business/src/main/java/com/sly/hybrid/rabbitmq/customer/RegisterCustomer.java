package com.sly.hybrid.rabbitmq.customer;

import cn.hutool.core.lang.UUID;
import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.sly.hybrid.business.dict.mapper.DicCodeMapper;
import com.sly.hybrid.business.dict.model.dto.AccountActiveExtDto;
import com.sly.hybrid.business.dict.model.po.DicCode;
import com.sly.hybrid.business.email.mapper.EmailTemplateMapper;
import com.sly.hybrid.business.email.model.po.EmailTemplate;
import com.sly.hybrid.business.user.model.po.User;
import com.sly.hybrid.common.redis.RedisHelper;
import com.sly.hybrid.constant.DicCodeConstant;
import com.sly.hybrid.constant.RabbitmqConst;
import com.sly.hybrid.constant.RedisConst;
import com.sly.myplugin.email.model.MailInfo;
import com.sly.myplugin.email.sender.EmailSender;
import org.springframework.amqp.rabbit.annotation.*;
import org.springframework.amqp.support.AmqpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.TimeUnit;

/**
 * 注册消费者
 *
 * @author SLY
 * @date 2022/1/4
 */
@Component
public class RegisterCustomer {

    @Resource
    private DicCodeMapper dicCodeMapper;
    @Resource
    private EmailTemplateMapper emailTemplateMapper;

    @Autowired
    private EmailSender emailSender;
    @Autowired
    private RedisHelper redisHelper;

    /** 注册后发送激活邮件 */
    @RabbitHandler
    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(value = "register-queue", durable = "false"),
            key = "register",
            exchange = @Exchange(value = RabbitmqConst.AMQ_DIRECT, ignoreDeclarationExceptions = "true")))
    @Transactional(rollbackFor = Exception.class)
    public void onRegister(Message<User> message, Channel channel) throws Exception {
        User user = message.getPayload();
        Long deliveryTag = (Long) message.getHeaders().get(AmqpHeaders.DELIVERY_TAG);
        // 创建激活码
        String activeCode = UUID.randomUUID().toString();
        redisHelper.set(RedisConst.ACTIVE_CODE_PREFIX + activeCode, user.getId(), 24, TimeUnit.HOURS);
        // 组装激活url
        DicCode dicCode = dicCodeMapper.findByCode(DicCodeConstant.EMAIL_ACCOUNT_ACTIVE_URL);
        AccountActiveExtDto accountActiveExtDto = JSONUtil.parseObj(dicCode.getExtended()).toBean(AccountActiveExtDto.class);
        String activeUrl = accountActiveExtDto.getActiveUrl();
        // 组装邮件内容
        EmailTemplate emailTemplate = emailTemplateMapper.findByType(DicCodeConstant.EMAIL_ACCOUNT_ACTIVE);
        String content = emailTemplate.getContent();
        content = content.replace("${activeUrl}", activeUrl);
        content = content.replace("${userId}", user.getId().toString());
        content = content.replace("${activeCode}", activeCode);
        // 发送激活邮件
        MailInfo mailInfo = new MailInfo();
        mailInfo.setSubject("hybrid激活邮件");
        mailInfo.setContent(content);
        mailInfo.addAddressee(user.getAccount());
        emailSender.sendHtmlEmail(mailInfo);

        // 手工ACK
        if (deliveryTag != null) {
            channel.basicAck(deliveryTag, false);
        }
    }

}
