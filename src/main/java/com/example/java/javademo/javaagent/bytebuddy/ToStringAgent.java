package com.example.java.javademo.javaagent.bytebuddy;

import lombok.ToString;
import net.bytebuddy.agent.builder.AgentBuilder;
import net.bytebuddy.description.type.TypeDescription;
import net.bytebuddy.dynamic.DynamicType;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.matcher.ElementMatchers;

import java.lang.instrument.Instrumentation;

/**
 * @Author: songyalong
 * @Description: 创建java agent
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class ToStringAgent {
    public static void premain(String args, Instrumentation instrumentation){
        new AgentBuilder.Default()
                .type((AgentBuilder.RawMatcher) ElementMatchers.annotationType(ToString.class))
                .transform(new AgentBuilder.Transformer(){

                    @Override
                    public DynamicType.Builder<?> transform(DynamicType.Builder<?> builder, TypeDescription typeDescription, ClassLoader classLoader) {
                        return builder.method(ElementMatchers.named("toString")).intercept(FixedValue.value("toString"));
                    }
                }).installOn(instrumentation);
    }
}
