package com.example.java.javademo.javaagent.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.agent.ByteBuddyAgent;
import net.bytebuddy.dynamic.loading.ClassReloadingStrategy;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class ReloadClassTest {
    public static void main(String[] args) {
        ByteBuddyAgent.install();
        Foo foo = new Foo();
        new ByteBuddy().redefine(Bar.class)
                .name(Foo.class.getName())
                .make()
                .load(Foo.class.getClassLoader(), ClassReloadingStrategy.fromInstalledAgent());
        System.out.println(foo.m());
    }
}
