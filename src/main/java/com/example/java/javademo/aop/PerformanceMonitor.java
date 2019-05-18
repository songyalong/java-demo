package com.example.java.javademo.aop;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class PerformanceMonitor {
    private static ThreadLocal<MethodPerformace> threadLocal = new ThreadLocal<MethodPerformace>();

    public static void begin(String method){
        System.out.println("begin monitor");
        MethodPerformace methodPerformace = new MethodPerformace(method);
        threadLocal.set(methodPerformace);
    }

    public static void end(){
        System.out.println("end monitor");
        MethodPerformace methodPerformace = threadLocal.get();
        methodPerformace.printPerformance();

    }
}
