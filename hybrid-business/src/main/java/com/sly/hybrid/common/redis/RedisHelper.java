package com.sly.hybrid.common.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.time.Duration;
import java.time.Instant;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * redis helper
 *
 * @author SLY
 * @date 2021/12/30
 */
@Component
public class RedisHelper {

    @Autowired
    @Qualifier("redis01StringRedisTemplate")
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    @Qualifier("redis01RedisTemplate")
    private RedisTemplate<Serializable, Object> redisTemplate;

    /**
     * 根据键取值
     *
     * @param key 键
     * @return {@link Object}
     * @author SLY
     * @date 2021/12/30
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 根据键取值
     *
     * @param key 键
     * @return {@link T}
     * @author SLY
     * @date 2021/12/30
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        return (T) redisTemplate.opsForValue().get(key);
    }

    /**
     * 设置值
     *
     * @param key   键
     * @param value 值
     * @author SLY
     * @date 2021/12/30
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 设置值
     *
     * @param key      键
     * @param value    值
     * @param timeout  超时时间
     * @param timeUnit 时间单位
     * @author SLY
     * @date 2021/12/30
     */
    public void set(String key, Object value, long timeout, TimeUnit timeUnit) {
        redisTemplate.opsForValue().set(key, value, timeout, timeUnit);
    }

    /**
     * 设置值
     *
     * @param key      键
     * @param value    值
     * @param duration 持续时间
     * @author SLY
     * @date 2021/12/30
     */
    public void set(String key, Object value, Duration duration) {
        redisTemplate.opsForValue().set(key, value, duration);
    }

    /**
     * 设置值
     *
     * @param key    键
     * @param value  值
     * @param offset 替换偏移量
     * @author SLY
     * @date 2021/12/30
     */
    public void set(String key, Object value, long offset) {
        redisTemplate.opsForValue().set(key, value, offset);
    }

    /**
     * key 是否存在
     *
     * @param key 键
     * @return boolean
     * @author SLY
     * @date 2021/12/30
     */
    public boolean exists(String key) {
        return Boolean.TRUE.equals(redisTemplate.hasKey(key));
    }

    /**
     * 设置key过期时间
     *
     * @param key     键
     * @param timeOut 过期时间
     * @author SLY
     * @date 2021/12/30
     */
    public void setExpire(String key, Duration timeOut) {
        redisTemplate.expire(key, timeOut);
    }

    /**
     * 设置key过期时间
     *
     * @param key     键
     * @param timeOut 过期时间
     * @author SLY
     * @date 2021/12/30
     */
    public void setExpire(String key, long timeOut) {
        redisTemplate.expire(key, timeOut, TimeUnit.SECONDS);
    }

    /**
     * 设置key过期时间
     *
     * @param key      键
     * @param timeOut  过期时间
     * @param timeUnit 时间单位
     * @author SLY
     * @date 2021/12/30
     */
    public void setExpire(String key, long timeOut, TimeUnit timeUnit) {
        redisTemplate.expire(key, timeOut, timeUnit);
    }

    /**
     * 设置过期时间点
     *
     * @param key  键
     * @param date 过期时间点
     * @author SLY
     * @date 2021/12/30
     */
    public void setExpireAt(String key, Date date) {
        redisTemplate.expireAt(key, date);
    }

    /**
     * 设置过期时间点
     *
     * @param key     键
     * @param instant 过期时间点
     * @author SLY
     * @date 2021/12/30
     */
    public void setExpireAt(String key, Instant instant) {
        redisTemplate.expireAt(key, instant);
    }

    /**
     * 删除值
     *
     * @param key 键
     * @author SLY
     * @date 2022/1/2
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }
}
