package com.example.java.javademo.collection;

import com.alibaba.fastjson.JSONArray;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestArrayList {
    public static void main(String[] args) {
       List<String> list = new LinkedList<>();
       list.add("amy");
       list.add("Bob");
       list.add("Carl");
        Iterator<String> iterator = list.iterator();
        iterator.next();
        iterator.next();
        iterator.remove();
        System.out.println(JSONArray.toJSONString(list));
    }
}
