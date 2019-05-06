package com.example.java.javademo.controller;

import java.util.Map;
import java.util.Properties;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
        Properties properties = System.getProperties();
        System.out.println(properties);

        Map<String, String> getenv = System.getenv();
        System.out.println(getenv);
    }
}
