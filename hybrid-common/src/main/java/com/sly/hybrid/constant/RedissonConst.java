package com.sly.hybrid.constant;

/**
 * redisson 分布式锁常量
 * @author SLY
 * @date 2022/1/4
 */
public class RedissonConst {
    /**
     * 操作用户分布式锁前缀
     * 1.注册使用account作为锁后缀
     */
    public static final String USER_PREFIX = "redisson:user:";
}
