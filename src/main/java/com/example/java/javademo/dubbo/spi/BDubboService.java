package com.example.java.javademo.dubbo.spi;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class BDubboService implements DubboService {
    @Override
    public void sayHi() {
        System.out.println("BDubboService");
    }
}
