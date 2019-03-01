package com.example.java.javademo.collection;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

/**
 * @Author: songyalong
 * @Description: test collection hashSet
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestHashSet {
    public static void main(String[] args) {

    }
    public void testHashSet(){
        Set<String> test = new HashSet<>();
        test.add("a");
        test.add("b");
        test.add("c");

        Collections.synchronizedSet(new HashSet<>());
    }
}
