package com.example.java.javademo.spi;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class BService implements Service {
    @Override
    public void sayHi() {
        System.out.println("B say Hi!");
    }
}
