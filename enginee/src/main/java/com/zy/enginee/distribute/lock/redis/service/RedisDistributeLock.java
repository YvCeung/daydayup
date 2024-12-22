package com.zy.enginee.distribute.lock.redis.service;

/**
 * @Description 用Redis实现分布式锁
 * @Author zy
 * @Date 2024/12/21 23:13
 **/
public interface RedisDistributeLock {

    /**
     * 获取锁
     * @param key
     * @return
     */
    boolean getLock(String key);

    /**
     * 实现类方式
     */
    String getType();
}
