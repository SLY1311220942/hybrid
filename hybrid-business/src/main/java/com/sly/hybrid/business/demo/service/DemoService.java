package com.sly.hybrid.business.demo.service;

import com.sly.myplugin.base.result.Result;
import com.sly.myplugin.email.model.MailInfo;
import com.sly.myplugin.email.sender.EmailSender;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


/**
 * @author SLY
 * @date 2021/12/7
 */
@Slf4j
@Service
public class DemoService {

    @Autowired
    private EmailSender emailSender;

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

    /**
     * 发送邮件demo
     *
     * @author SLY
     * @date 2022/1/5
     * @return {@link Result}
     */
    public Result<?> sendEmail() {
        // 发送邮件
        MailInfo mailInfo = new MailInfo();
        mailInfo.setContent("<h1>sendHtmlEmail测试内容</h1>");
        mailInfo.setSubject("hybrid激活邮件");
        mailInfo.addAddressee("1311220942@qq.com");
        emailSender.sendHtmlEmail(mailInfo);
        return Result.success();
    }
}
