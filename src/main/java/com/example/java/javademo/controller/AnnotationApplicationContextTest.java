package com.example.java.javademo.controller;

import com.example.java.javademo.config.RedisConfiguration;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class AnnotationApplicationContextTest {
    public static void main(String[] args) {
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(RedisConfiguration.class);
        RedisTemplate bean = applicationContext.getBean(RedisTemplate.class);

    }
}
