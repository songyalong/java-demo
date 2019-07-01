package com.example.java.javademo.redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisSentinelPool;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: songyalong
 * @Description: 哨兵模式
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestSentinel {
    public static void main(String[] args) {
        Set<String> sentinelSet = new HashSet<>();
        sentinelSet.add("192.168.137.2:26379");
        sentinelSet.add("192.168.137.2:26380");
        sentinelSet.add("192.168.137.2:26381");
        JedisSentinelPool jedisSentinelPool = new JedisSentinelPool("mymaster", sentinelSet);
        Jedis jedis = jedisSentinelPool.getResource();
        jedis.set("key", "key");
        String value = jedis.get("key");
        System.out.println("value = "+ value);
    }
}
