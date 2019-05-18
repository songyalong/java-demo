package com.example.java.javademo.aop;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class PerformanceHandler implements InvocationHandler {

    private Object object;

    PerformanceHandler(Object object){
        this.object = object;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        PerformanceMonitor.begin(proxy.getClass().getName()+"." + method.getName());
        Object invoke = method.invoke(object, args);
        PerformanceMonitor.end();
        return invoke;
    }
}
