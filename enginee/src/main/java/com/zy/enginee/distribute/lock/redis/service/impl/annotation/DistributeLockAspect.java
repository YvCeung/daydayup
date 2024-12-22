package com.zy.enginee.distribute.lock.redis.service.impl.annotation;

import com.zy.enginee.distribute.lock.redis.constant.CacheConstant;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.StandardReflectionParameterNameDiscoverer;
import org.springframework.expression.EvaluationContext;
import org.springframework.expression.Expression;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;
import org.aspectj.lang.annotation.Aspect;

import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * @Description 分布式锁切面类逻辑
 * @Author zy
 * @Date 2024/11/22 19:37
 **/
@Aspect
@Component
public class DistributeLockAspect {
    
    private static final String JING_HAO = "#";

    private static final Logger logger = LoggerFactory.getLogger(DistributeLockAspect.class);

    @Autowired
    private RedissonClient redissonClient;

    /**
     * AOP 环绕通知：拦截带有 @DistributeLock 注解的方法，执行分布式锁逻辑。
     */
    @Around("@annotation(com.zy.enginee.distribute.lock.redis.service.impl.annotation.DistributeLock)")
    public Object process(ProceedingJoinPoint pjp) throws Exception {
        Object response = null;
        //获取被拦截的方法并得到注解
        Method method = ((MethodSignature) pjp.getSignature()).getMethod();
        DistributeLock distributeLock = method.getAnnotation(DistributeLock.class);

        //解析key
        String key = resolveLockKey(pjp, distributeLock, method);
        //拼接最终的lockKey
        String lockKey = distributeLock.scene() + JING_HAO + key;

        int expireTime = distributeLock.expireTime();
        int waitTime = distributeLock.waitTime();

        RLock rLock = redissonClient.getLock(lockKey);
        boolean lockAcquired = tryAcquireLock(rLock, lockKey, expireTime, waitTime);

        if (!lockAcquired) {
            logger.warn("Lock acquisition failed for key: {}", lockKey);
            throw new RuntimeException("Acquiring lock failed for key: " + lockKey);
        }

        try {
            logger.info("Lock acquired successfully for key: {}", lockKey);
            response = pjp.proceed();
        } catch (Throwable e) {
            throw new Exception(e);
        } finally {
            releaseLock(rLock, lockKey, expireTime);
        }

        return response;
    }

    private String resolveLockKey(ProceedingJoinPoint pjp, DistributeLock distributeLock, Method method) {
        String key = distributeLock.key();
        if (CacheConstant.DEFAULT_LOCK_KEY.equals(key)) {
            if (CacheConstant.DEFAULT_LOCK_KEY.equals(distributeLock.keyExpression())) {
                throw new IllegalArgumentException("No lock key found...");
            }
            key = evaluateSpelExpression(pjp, distributeLock.keyExpression(), method);
        }
        return key;
    }

    /**
     * 通过 SpEL 表达式解析获取锁的键值。
     *
     * @param pjp 当前方法调用的连接点，包含方法参数信息
     * @param expressionString SpEL 表达式字符串，通常是注解中定义的 keyExpression
     * @param method 当前执行的方法
     * @return 返回解析后的锁的键值
     */
    private String evaluateSpelExpression(ProceedingJoinPoint pjp, String expressionString, Method method) {
        // 创建 SpEL 表达式解析器
        SpelExpressionParser parser = new SpelExpressionParser();

        // 解析给定的 SpEL 表达式字符串
        Expression expression = parser.parseExpression(expressionString);

        // 创建一个标准的 EvaluationContext，用于绑定方法参数
        EvaluationContext context = new StandardEvaluationContext();

        // 获取当前方法的所有参数
        Object[] args = pjp.getArgs();

        // 使用 Spring 的反射机制获取方法的参数名
        StandardReflectionParameterNameDiscoverer discoverer = new StandardReflectionParameterNameDiscoverer();
        String[] parameterNames = discoverer.getParameterNames(method);

        // 将方法参数名和对应的值绑定到 SpEL 上下文中
        if (parameterNames != null) {
            for (int i = 0; i < parameterNames.length; i++) {
                // 将方法参数名和参数值设置到 SpEL 上下文
                context.setVariable(parameterNames[i], args[i]);
            }
        }

        // 使用绑定的参数上下文解析 SpEL 表达式，并返回计算结果
        return String.valueOf(expression.getValue(context));
    }


    private boolean tryAcquireLock(RLock rLock, String lockKey, int expireTime, int waitTime) throws InterruptedException {
        boolean lockResult = false;

        //默认值代表不等待
        if (waitTime == CacheConstant.DEFAULT_WAIT_TIME) {
            lockResult = acquireLockWithoutWait(rLock, lockKey, expireTime);
        } else {
            lockResult = acquireLockWithWait(rLock, lockKey, expireTime, waitTime);
        }

        return lockResult;
    }

    /**
     * 不等待时间上锁
     * @param rLock
     * @param lockKey
     * @param expireTime
     * @return
     */
    private boolean acquireLockWithoutWait(RLock rLock, String lockKey, int expireTime) {
        boolean result = false;

        if (expireTime == CacheConstant.DEFAULT_EXPIRE_TIME) {
            logger.info("Locking for key: {}", lockKey);
            rLock.lock();
            result = true;
        } else {
            logger.info("Locking for key: {}, expire: {}ms", lockKey, expireTime);
            rLock.lock(expireTime, TimeUnit.MILLISECONDS);
            result = true;
        }

        return result;
    }

    /**
     * 等待时间上锁
     * @param rLock
     * @param lockKey
     * @param expireTime
     * @param waitTime
     * @return
     * @throws InterruptedException
     */
    private boolean acquireLockWithWait(RLock rLock, String lockKey, int expireTime, int waitTime) throws InterruptedException {
        boolean result = false;

        if (expireTime == CacheConstant.DEFAULT_EXPIRE_TIME) {
            logger.info("Trying to lock for key: {}, wait: {}ms", lockKey, waitTime);
            result = rLock.tryLock(waitTime, TimeUnit.MILLISECONDS);
        } else {
            logger.info("Trying to lock for key: {}, expire: {}ms, wait: {}ms", lockKey, expireTime, waitTime);
            result = rLock.tryLock(waitTime, expireTime, TimeUnit.MILLISECONDS);
        }

        return result;
    }

    private void releaseLock(RLock rLock, String lockKey, int expireTime) {
        rLock.unlock();
        logger.info("Unlocking for key: {}, expire: {}ms", lockKey, expireTime);
    }
}
