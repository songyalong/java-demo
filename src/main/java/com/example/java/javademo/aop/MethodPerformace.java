package com.example.java.javademo.aop;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class MethodPerformace {
    private Long begin;
    private Long end;
    private String methodName;
    MethodPerformace(String methodName){
        this.methodName = methodName;
        this.begin = System.currentTimeMillis();
    }
    public void printPerformance(){
        end = System.currentTimeMillis();
        long elapse = end - begin;
        System.out.println(this.methodName+"花费："+ elapse);
    }

}
