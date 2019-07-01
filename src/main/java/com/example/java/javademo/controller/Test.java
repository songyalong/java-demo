package com.example.java.javademo.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@RestController
public class Test {

    @GetMapping(value = "test/")
    public Object test(){
        Map<String, Object> data  = new HashMap<>(8);
        data.put("test", "test");
        System.out.println(JSONObject.toJSON(data).toString());
        return data;
    }
}
