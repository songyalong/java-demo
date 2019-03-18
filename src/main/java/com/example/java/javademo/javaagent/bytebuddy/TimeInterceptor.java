package com.example.java.javademo.javaagent.bytebuddy;

import net.bytebuddy.implementation.bind.annotation.Origin;
import net.bytebuddy.implementation.bind.annotation.RuntimeType;
import net.bytebuddy.implementation.bind.annotation.SuperCall;
import net.bytebuddy.jar.asm.commons.Method;

import java.util.concurrent.Callable;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TimeInterceptor {

    @RuntimeType
    public static Object intercept(@Origin Method method, @SuperCall Callable<?> callable){

        long start = System.currentTimeMillis();

        try {
            return callable.call();
        } catch (Exception e) {
            e.printStackTrace();
        }finally {
            System.out.println(method + ": took " + (System.currentTimeMillis() - start) + "ms");
        }

        return null;
    }
}
