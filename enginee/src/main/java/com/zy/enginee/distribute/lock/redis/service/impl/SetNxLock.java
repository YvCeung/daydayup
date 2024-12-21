package com.zy.enginee.distribute.lock.redis.service.impl;

import com.zy.enginee.distribute.lock.redis.enums.LockWayEnum;
import org.redisson.api.RBucket;
import org.redisson.api.RedissonClient;
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

    @Autowired
    private RedissonClient redissonClient;

    @Override
    public boolean getLock(String lockKey) {
        try {
            // 获取对应的 RBucket，模拟 Redis 的 String 类型
            RBucket<String> bucket = redissonClient.getBucket(lockKey);

            // 尝试设置锁
            boolean isLockAcquired = bucket.setIfAbsent(LOCK_VALUE, LOCK_EXPIRE_TIME, TimeUnit.SECONDS);

            if (isLockAcquired) {
                // 获取锁成功，执行需要同步的任务
                System.out.println("Lock acquired, performing critical task...");
                try {
                    // 模拟执行任务
                    Thread.sleep(5000); // 模拟任务执行时间
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
            } else {
                // 获取锁失败，执行其他操作
                System.out.println("Failed to acquire lock, performing alternative task...");
            }

        } finally {
            // 释放锁：确保只释放自己持有的锁
            RBucket<String> bucket = redisson.getBucket(lockKey);
            String currentLockValue = bucket.get();
            if (lockValue.equals(currentLockValue)) {
                // 如果锁的值和我们设置的值一致，说明是当前线程持有锁
                bucket.delete(); // 删除锁键，释放锁
                System.out.println("Lock released.");
            }
        }

        // 关闭 Redisson 客户端
        redissonClient.shutdown();
    }

    @Override
    public String getType() {
        return LockWayEnum.REDIS_SETNX_LOCK.name();
    }
}
