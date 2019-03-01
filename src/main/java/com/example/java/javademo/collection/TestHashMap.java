package com.example.java.javademo.collection;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestHashMap {
    public static void main(String[] args) {
        TestHashMap testHashMap = new TestHashMap();
        testHashMap.test();
        System.out.println(testHashMap.hashCode());
    }

    public void removeItem(){
        Map<String, String> hashMap = new HashMap<>();
        hashMap.put("", "");
    }

    public void test(){
        int a = 10 - 1 ;
        a |= a >>> 1;
//        a = a >>> 2;
        System.out.println(a);
    }
}
