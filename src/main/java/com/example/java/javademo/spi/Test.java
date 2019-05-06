package com.example.java.javademo.spi;

import java.util.ServiceLoader;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
        ServiceLoader<Service> services = ServiceLoader.load(Service.class);
        for(Service service : services){
            service.sayHi();
        }
    }
}
