package com.example.java.javademo.spi;

import java.util.HashSet;
import java.util.Set;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Test2 {
    public static void main(String[] args) {
        Set<Integer> set = new HashSet<>();
        set.add(333);
        set.add(333);
        set.add(333);
        set.add(333);
        System.out.println(set);
    }
}
