package com.example.java.javademo.javaagent;

import com.example.java.javademo.javaagent.instrument.TransformerClass;

import java.lang.instrument.Instrumentation;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class AgentMain {
    public static void premain(String args, Instrumentation instrumentation){
        System.out.println("参数 ： "+ args);
        instrumentation.addTransformer(new TransformerClass());
    }
}
