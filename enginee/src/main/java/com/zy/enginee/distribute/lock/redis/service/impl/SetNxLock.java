package com.zy.enginee.distribute.lock.redis.service.impl;

import com.zy.enginee.distribute.lock.redis.enums.LockWayEnum;
import com.zy.enginee.distribute.lock.redis.service.RedisDistributeLock;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

import static com.zy.enginee.distribute.lock.redis.constant.CacheConstant.LOCK_EXPIRE_TIME;
import static com.zy.enginee.distribute.lock.redis.constant.CacheConstant.LOCK_VALUE;

/**
 * @Description 基于setnx 命令 实现的分布式锁
 * @Author zy
 * @Date 2024/12/18 09:47
 **/
@Service
public class SetNxLock implements RedisDistributeLock {

    private static final Logger logger = LoggerFactory.getLogger(SetNxLock.class);

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean getLock(String lockKey) {
        logger.info("开始获取分布式锁，key为:{}", lockKey);

        // 获取对应的 RBucket，模拟 Redis 的 String 类型
        RBucket<String> bucket = redissonClient.getBucket(lockKey);

        try {
            // 尝试设置锁，相当于 Redis 的 SETNX 命令
            boolean isLockAcquired = bucket.setIfAbsent(LOCK_VALUE);

            if (isLockAcquired) {
                // 获取锁成功
                logger.info("获取锁成功，key为:{}", lockKey);

                // 设置过期时间，防止死锁
                boolean expireSuccess = bucket.expire(LOCK_EXPIRE_TIME, TimeUnit.SECONDS);

                if (expireSuccess) {
                    logger.info("设置过期时间成功，key:{}, 有效期:{}秒", lockKey, LOCK_EXPIRE_TIME);
                } else {
                    logger.warn("设置过期时间失败，key:{}, 有效期:{}秒", lockKey, LOCK_EXPIRE_TIME);
                }

                return true;
            } else {
                // 获取锁失败
                logger.error("获取锁失败，key为:{}，锁可能被其他客户端持有", lockKey);
            }
        } catch (Exception e) {
            // 处理 Redisson 操作的异常
            logger.error("获取锁时发生异常，key为:{}，错误信息: {}", lockKey, e.getMessage());
        }

        return false;
    }

    @Override
    public String getType() {
        return LockWayEnum.REDIS_SETNX_LOCK.name();
    }
}
