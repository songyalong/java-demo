package com.example.java.javademo.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: songyalong
 * @Description: 分布式锁, 参考链接;https://www.cnblogs.com/linjiqin/p/8003838.html
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Component
public class RedisDistributedLock {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 该方法使用了setnx来实现
     * 在主备的redis情况下，是不安全
     * @param key 键值
     * @param value value值
     * @return
     */
    public  boolean tryGetDistributedLock(String key, String value){

        Boolean boo = redisTemplate.opsForValue().setIfAbsent(key, value, 3, TimeUnit.SECONDS);
        return boo;
    }

}
