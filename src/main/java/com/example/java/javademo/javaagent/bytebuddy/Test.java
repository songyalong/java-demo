package com.example.java.javademo.javaagent.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
            Test test = new Test();
            test.test();
    }

    public void test(){
        Class<?> dynamicType = new ByteBuddy()
                .subclass(Object.class) // 定义给一个父类
                .method(ElementMatchers.named("toString")) //拦截的方法
                .intercept(FixedValue.value("Hello World!")) // 委托， 给一个固定值
                .name("example.Type")
                .make()
                .load(this.getClass().getClassLoader())
                .getLoaded();
        try {
            System.out.println(dynamicType.newInstance().getClass().getName());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
