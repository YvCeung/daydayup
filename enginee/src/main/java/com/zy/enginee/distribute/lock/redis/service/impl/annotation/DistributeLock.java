package com.zy.enginee.distribute.lock.redis.service.impl.annotation;

import com.zy.enginee.distribute.lock.redis.constant.CacheConstant;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description
 * @Author zy
 * @Date 2024/11/22 18:53
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DistributeLock {

    /**
     * 锁的场景，用于区分不同业务场景下的锁。
     */
    String scene();

    /**
     * 锁的 key，默认值为 NONE，表示需要通过 keyExpression 动态生成。
     */
    String key() default CacheConstant.DEFAULT_LOCK_KEY;

    /**
     * 锁的 key 使用 SpEL 表达式，动态解析锁的 key。
     */
    String keyExpression() default CacheConstant.DEFAULT_LOCK_KEY;

    /**
     * 锁的过期时间，单位为毫秒。
     */
    int expireTime() default CacheConstant.DEFAULT_EXPIRE_TIME;

    /**
     * 获取锁的最大等待时间，单位为毫秒。
     */
    int waitTime() default CacheConstant.DEFAULT_WAIT_TIME;
}

