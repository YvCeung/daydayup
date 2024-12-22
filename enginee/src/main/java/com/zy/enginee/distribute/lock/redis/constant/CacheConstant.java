package com.zy.enginee.distribute.lock.redis.constant;

/**
 * @Description
 * @Author zy
 * @Date 2024/12/21 23:26
 **/
public class CacheConstant {

    /**
     * 锁的值，用于标识锁已经被占用。
     */
    public static final String LOCK_VALUE = "distributeLockValue";

    /**
     * 默认锁的 key，如果没有特别指定，使用该值。
     */
    public static final String DEFAULT_LOCK_KEY = "NONE";

    /**
     * 默认锁的过期时间（单位为秒）。
     */
    public static final Long LOCK_EXPIRE_TIME = 10L;

    /**
     * 默认的锁过期时间，表示不设置超时时间。
     * 因为Redisson底层的自动续期机制会判断只要大于0的时候就会自动续期
     */
    public static final Integer DEFAULT_EXPIRE_TIME = -1;

    /**
     * 默认的等待时间，表示不等待。
     */
    public static final Integer DEFAULT_WAIT_TIME = Integer.MIN_VALUE;
}

