package com.example.java.javademo.classloader;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class ClassLoaderTest {
    public static void main(String[] args) {
        ClassLoader contextClassLoader = Thread.currentThread().getContextClassLoader();
        System.out.println("current loader : "+ contextClassLoader);
        System.out.println("parent loader : "+ contextClassLoader.getParent());
        System.out.println("parent parent loader : "+ contextClassLoader.getParent().getParent());


    }
}
