package com.example.java.javademo.controller;

import com.example.java.javademo.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.DefaultTypedTuple;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@RestController
public class RedisController {
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @Autowired
    private RedisTemplate redisTemplate;

    @PostMapping(value = "/set/{key}/{value}")
    public String setString(@PathVariable("key") String key, @PathVariable("value") String value){
        stringRedisTemplate.opsForValue().set(key, value);
        return "SUCCESS";
    }

    @PostMapping(value = "/set/{key}/{value}/{ttl}/")
    public String setString(@PathVariable("key") String key, @PathVariable("value")String value, @PathVariable("ttl") Integer ttl){
        stringRedisTemplate.opsForValue().set(key, value, ttl, TimeUnit.SECONDS);
        return "SUCCESS";
    }

    @GetMapping(value = "/get/{key}/")
    public String getString(@PathVariable("key") String key){
        return stringRedisTemplate.opsForValue().get(key);
    }

    @PostMapping(value = "/del/{id}/")
    public String delString(@PathVariable(name = "id") String id){
        stringRedisTemplate.delete(id);
        return "SUCCESS";
    }
    @GetMapping(value = "/exist/{id}")
    public Boolean existsString(@PathVariable(name = "id") String key){
        return stringRedisTemplate.hasKey(key);
    }


    @PostMapping(value = "/set/user/")
    public String setEntity(){
        User user = new User();
        user.setId(123);
        user.setName("123123");
        user.setPassword("123123");
        redisTemplate.opsForValue().set(user.getId(), user);
        return "SUCCESS";
    }




    @GetMapping(value = "user/{id}")
    public User getUser(@PathVariable("id") String id){
        System.out.println(id);
        redisTemplate.opsForValue().get(id);
        System.out.println(redisTemplate.opsForValue().get(id));
        return null;
    }

    @PostMapping(value = "/list/set/user/")
    public String setListEntity(){
        User user = new User();
        user.setId(1);
        user.setPassword("123");
        redisTemplate.opsForList().leftPush("user", user);
        return "SUCCESS";
    }

    @GetMapping(value = "/list/get/user/")
    public User getListEntity(){
        User user = (User) redisTemplate.opsForList().leftPop("user");
        return user;
    }

    @PostMapping(value = "/set/set/user")
    public String setSetEntity(){
        User user = new User();
        user.setId(123);
        user.setName("123");
        user.setPassword("123");
        redisTemplate.opsForSet().add("users", user);
        return "SUCCESS";
    }

    @GetMapping(value = "/test/set/")
    public Map<String, Object> getSetUser(){
        String key = "users";
        User user = new User();
        user.setId(123);
        user.setName("123");
        user.setPassword("123");
        redisTemplate.opsForSet().add(key, user);
        // 元素是否存在集合中
        Boolean member = redisTemplate.opsForSet().isMember(key, user);
        System.out.println(member);
        // 获取集合数量
        Long size = redisTemplate.opsForSet().size(key);
        System.out.println("集合数量="+ size);

        // 获取集合中的所有元素，并打印
        Set members = redisTemplate.opsForSet().members(key);
        members.forEach(obj-> System.out.println(obj));

        // 添加多个元素
        redisTemplate.opsForSet().add(key, 1, 2, 3, 4);

        // 随机获取一个元素

        Object o = redisTemplate.opsForSet().randomMember(key);
        System.out.println(o);
        // 随机返回多个元素
        List list = redisTemplate.opsForSet().randomMembers(key, 3);
        list.forEach(obj-> System.out.println(obj));
        // 移除元素, 返回移除的个数
        Long remove = redisTemplate.opsForSet().remove(key, 5);
        System.out.println("remove = "+ remove);


        // 多个集合运算
        redisTemplate.opsForSet().add("mySet", "a", "b", "c", "d");
        redisTemplate.opsForSet().add("mySet2", "c");
        redisTemplate.opsForSet().add("mySet3", "a", "c", "e");

        // 取mySet集合与mySet2， mySet3的差集
        Set difference = redisTemplate.opsForSet().difference("mySet", "mySet2");
        difference.forEach(obj-> System.out.println("差集 = "+ obj));

        //取集合的交集
        Set intersect = redisTemplate.opsForSet().intersect("mySet", "mySet2");
        intersect.forEach(obj-> System.out.println("交集 = "+ obj));

        // 集合并集
        Set union = redisTemplate.opsForSet().union("mySet", "mySet2");
        union.forEach(obj-> System.out.println("并集->"+ obj));

        // 删除集合
        Boolean delete = redisTemplate.delete(key);
        System.out.println("删除成功"+delete);

        return null;
    }

    @GetMapping(value = "/test/zset/")
    public void testZset(){
        redisTemplate.opsForZSet().add("myzset", "one", 1);
        redisTemplate.opsForZSet().add("myzset", "two", 2);
        redisTemplate.opsForZSet().add("myzset", "three", 3);
        redisTemplate.opsForZSet().add("myzset", "four", 4);
        redisTemplate.opsForZSet().add("myzset", "five", 5);
        // 获取集合的所有值
        Set myzset = redisTemplate.opsForZSet().range("myzset", 0, -1);
        System.out.println("###遍历集合所有元素###");
        myzset.forEach(obj-> System.out.println(obj));
        // 遍历集合包括score
        Set<DefaultTypedTuple> myzset1 = redisTemplate.opsForZSet().rangeWithScores("myzset", 0 , -1);
        System.out.println("###遍历集合（包含score）###");
        myzset1.forEach(obj-> System.out.println("value="+ obj.getValue()+", score="+ obj.getScore()));
        // 获取元素索引值
        Long rank = redisTemplate.opsForZSet().rank("myzset", "one");
        System.out.println("one score = "+ rank);
        Long rank1 = redisTemplate.opsForZSet().rank("myzset", "two");
        System.out.println("two score = "+ rank1);
        // 获取集合的数量
        Long zcard = redisTemplate.opsForZSet().zCard("myzset");
        System.out.println("zcard = "+ zcard);
        // 获取某个值的score
        Double score = redisTemplate.opsForZSet().score("myzset", "three");
        System.out.println(" three score = "+ score);

        // 获取分数满足1<score<=2的成员
        Set myset = redisTemplate.opsForZSet().rangeByScore("myzset", 1, 2);
        System.out.println("获取满足 1< score <= 2的元素");
        myset.forEach(obj-> System.out.println(obj));
        // 将成员one的score增加2
        Double aDouble = redisTemplate.opsForZSet().incrementScore("myzset", "one", 5);
        System.out.println("aDouble = "+aDouble);
        // 数据从高到低排序
        Set myzset2 = redisTemplate.opsForZSet().reverseRange("myzset", 0, -1);
        System.out.println("###数据从高到低排序###");
        myzset2.forEach(obj-> System.out.println(obj));

    }

    @GetMapping(value = "/test/hash/")
    public void testHash(){
        redisTemplate.opsForHash().put("myhash", "filed1", 1);
        redisTemplate.opsForHash().put("myhash", "filed2", 2);
        redisTemplate.opsForHash().put("myhash", "filed3", 3);

        // 获取元素filed1的值
        Object o = redisTemplate.opsForHash().get("myhash", "filed1");
        System.out.println("###获取filed1的值###");
        System.out.println(o);
        //获取hash数量
        Long count = redisTemplate.opsForHash().size("myhash");
        System.out.println("###获取hash数量###");
        System.out.println("count = "+ count);
        // 判断是否存在
        Boolean aBoolean = redisTemplate.opsForHash().hasKey("myhash", "filed1");
        System.out.println("filed1 key exists : "+ aBoolean);

        // 获取所有的key
        Set myhash = redisTemplate.opsForHash().keys("myhash");


        System.out.println("### 获取所有的key###");
        myhash.forEach(obj-> System.out.println(obj));
        // 获取所有的value
        List myhash1 = redisTemplate.opsForHash().values("myhash");
        System.out.println("###获取所有的value###");
        System.out.println(myhash1.size());
        myhash1.forEach(obj-> System.out.println(obj));

    }

    /**
     * 测试分布式锁
     */
    @GetMapping(value = "/distributed/")
    public void testDistributed(){
        Executors.newFixedThreadPool()
    }




}
