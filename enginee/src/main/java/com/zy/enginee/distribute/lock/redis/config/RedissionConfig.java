package com.zy.enginee.distribute.lock.redis.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Description redission配置类
 * @Author zy
 * @Date 2024/12/20 11:45
 **/
//@Configuration
public class RedissionConfig {

    private String redisAddr;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer().setAddress(redisAddr);

        return Redisson.create(config);
    }
}
