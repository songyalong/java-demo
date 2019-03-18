package com.example.java.javademo.javaagent.bytebuddy;

import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.method.MethodDescription;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;
import net.bytebuddy.utility.JavaModule;

import java.lang.instrument.Instrumentation;

/**
 * @Author: songyalong
 * @Description: 使用bytebuddy实现agent
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class MyAgent {
    public static void premain(String args, Instrumentation instrumentation){
        System.out.println("this is an perform monitor agent");
        AgentBuilder.Transformer transformer = new AgentBuilder.Transformer(){
            @Override
            public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader) {
                return builder.method(ElementMatchers.<MethodDescription>any()) // method 定义要拦截的方法
                        .intercept(MethodDelegation.to(TimeInterceptor.class)); // 委托哪个类拦截
            }
        };

        AgentBuilder.Listener listener = new AgentBuilder.Listener() {
            @Override
            public void onTransformation(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule, DynamicType dynamicType) {

            }

            @Override
            public void onIgnored(TypeDescription typeDescription, ClassLoader classLoader, JavaModule javaModule) {

            }

            @Override
            public void onError(String s, ClassLoader classLoader, JavaModule javaModule, Throwable throwable) {

            }

            @Override
            public void onComplete(String s, ClassLoader classLoader, JavaModule javaModule) {

            }
        };

        new AgentBuilder
                .Default()
                .type(ElementMatchers.nameStartsWith("com.example.java.javademo"))
                .transform(transformer)
                .with(listener)
                .installOn(instrumentation);


    }
}
