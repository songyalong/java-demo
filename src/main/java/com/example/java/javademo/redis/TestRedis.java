package com.example.java.javademo.redis;

import redis.clients.jedis.Jedis;

import java.util.List;
import java.util.Set;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestRedis {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("localhost");
//        testList(jedis);
        testGetKeys(jedis);
    }

    public static void testString(Jedis jedis){
        System.out.println(jedis.ping());
        jedis.set("name", "testString");
        String s = jedis.get("name");
        System.out.println("name = " + s);
        jedis.append("name", ", hello world");
        String s1 = jedis.get("name");
        System.out.println("name = " + s1);
    }

    public static void testList(Jedis jedis){
        jedis.lpush("site-list", "Runoob");
        jedis.lpush("site-list", "Google");
        jedis.lpush("site-list", "Taobao");
        // 输出
        List<String> lrange = jedis.lrange("site-list", 0, 2);
        for(String value : lrange){
            System.out.println(value);
        }
    }

    public static void testGetKeys(Jedis jedis){
        Set<String> keys = jedis.keys("*");
        for(String key : keys){
            System.out.println(key);
        }
    }



}
