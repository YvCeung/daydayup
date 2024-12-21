package com.zy.enginee.distribute.lock.redis.enums;

/**
 * @Description 分布式锁的实现方式枚举类型
 * @Author zy
 * @Date 2024/12/21 23:16
 **/
public enum LockWayEnum {
    REDIS_SETNX_LOCK("REDIS_SETNX","基于Redis的以SETNX最原始的命令实现"),
    REDIS_REDISSON_ANNOTATION_LOCK("REDIS_REDISSON_ANNOTATION","基于Redis的Redisson客户端以注解的方式实现"),

    MYSQL_LOCK("MYSQL_LOCK","基于MySQL的方式去实现");

    LockWayEnum(){

    }

    //有参构造器
    LockWayEnum(String code, String des) {
        this.code = code;
        this.des = des;
    }


    //这个就是属性
    private String code;

    private String des;

    public String getDes() {
        return des;
    }

}
