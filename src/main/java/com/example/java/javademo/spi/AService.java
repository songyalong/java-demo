package com.example.java.javademo.spi;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class AService implements Service {
    @Override
    public void sayHi() {
        System.out.println("A say Hi!");
    }
}
