package com.sly.hybrid.business.login.service;

import cn.hutool.crypto.digest.MD5;
import com.sly.hybrid.business.login.param.RegisterParam;
import com.sly.hybrid.business.user.mapper.UserMapper;
import com.sly.hybrid.business.user.model.po.User;
import com.sly.hybrid.constant.HybridRegexConst;
import com.sly.hybrid.constant.RabbitmqConst;
import com.sly.hybrid.constant.RedissonConst;
import com.sly.hybrid.constant.StatusConst;
import com.sly.hybrid.result.ResultCode;
import com.sly.hybrid.util.DesUtil;
import com.sly.myplugin.base.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 注册 service
 *
 * @author SLY
 * @date 2022/1/4
 */
@Slf4j
@Service
public class RegisterService {

    @Resource
    private UserMapper userMapper;

    @Autowired
    private RedissonClient redissonClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 注册
     *
     * @param param 注册参数 {@link RegisterParam}
     * @return {@link Result}
     * @author SLY
     * @date 2022/1/2
     */
    @Transactional(rollbackFor = Exception.class)
    public Result<?> register(RegisterParam param) {
        String account = DesUtil.decryptStr(param.getAccount());
        String password = DesUtil.decryptStr(param.getPassword());
        RLock lock = redissonClient.getLock(RedissonConst.USER_PREFIX + account);
        try {
            // 验证
            User existUser = userMapper.findUserByAccount(param.getAccount());
            if (existUser != null) {
                return Result.failed(ResultCode.REGISTER_ACCOUNT_EXIST);
            }
            if (!account.matches(HybridRegexConst.EMAIL)) {
                return Result.failed(ResultCode.REGISTER_ACCOUNT_EMAIL_ILLEGAL);
            }
            if (!password.matches(HybridRegexConst.USER_PASSWORD)) {
                return Result.failed(ResultCode.REGISTER_PASSWORD_ILLEGAL);
            }
            // 注册
            User user = new User();
            user.setAccount(account);
            user.setUsername(param.getUsername());
            user.setPassword(MD5.create().digestHex(password));
            user.setEmail(account);
            user.setCreateTime(new Date());
            user.setUpdateTime(new Date());
            user.setDelStatus(StatusConst.DEL_NO);
            user.setUserStatus(StatusConst.UserStatus.DRIFT.getCode());
            userMapper.add(user);
            // 通知发送激活邮件
            rabbitTemplate.convertAndSend(RabbitmqConst.AMQ_DIRECT, "register", user);

            return Result.success();
        } finally {
            lock.unlock();
        }

    }
}
