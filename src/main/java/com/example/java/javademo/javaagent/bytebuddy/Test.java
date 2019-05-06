package com.example.java.javademo.javaagent.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.ClassFileVersion;
import net.bytebuddy.implementation.DefaultMethodCall;
import net.bytebuddy.implementation.FixedValue;
import net.bytebuddy.implementation.MethodDelegation;
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
            test.userDefaultMethod();
    }

    /**
     * 调用默认方法
     */
    public void userDefaultMethod(){
        try {
            Object qux = new ByteBuddy(ClassFileVersion.JAVA_V8)
                    .subclass(Object.class)
                    .implement(First.class)
                    .implement(Second.class)
                    .method(ElementMatchers.named("qux")).intercept(DefaultMethodCall.prioritize(First.class))
                    .make().load(this.getClass().getClassLoader()).getLoaded().newInstance();

            First first = (First) qux;
            System.out.println(first.qux());

            Second second = (Second) qux;
            System.out.println(first.qux());

        }catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * 调用超类
     */
    public void useSuperMethod(){

    }


    /**
     * 委托调用
     */
    public void deletgation(){
        try {
            String hello = new ByteBuddy()
                    .subclass(Source.class)
                    .method(ElementMatchers.named("hello"))
                    .intercept(MethodDelegation.to(Target.class))
                    .make()
                    .load(this.getClass().getClassLoader())
                    .getLoaded()
                    .newInstance()
                    .hello("world");
            System.out.println(hello);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

    }

    /**
     * 动态
     */
    public void dynamicMethod(){
        try {
            Foo2 foo2 = new ByteBuddy().subclass(Foo2.class)
                    .method(ElementMatchers.isDeclaredBy(Foo2.class))
                    .intercept(FixedValue.value("One!"))
                    .method(ElementMatchers.named("foo"))
                    .intercept(FixedValue.value("Two"))
                    .method(ElementMatchers.named("foo").and(ElementMatchers.takesArguments(1)))
                    .intercept(FixedValue.value("Three!"))
                    .make()
                    .load(this.getClass().getClassLoader())
                    .getLoaded()
                    .newInstance();

            System.out.println(foo2.foo(2));
//            Foo2 foo2 = (Foo2) o;
//            System.out.println(foo2.foo());

        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义方法
     */
    public void defMethod(){
        try {
            Object object = new ByteBuddy().subclass(Object.class)
                    .name("example.Type")
                    .method(ElementMatchers.named("toString"))
                    .intercept(FixedValue.value("Hello， World!"))
                    .make()
                    .load(this.getClass().getClassLoader())
                    .getLoaded()
                    .newInstance();
            System.out.println(object.toString());
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    /**
     * 定义属性
     */
    public void defField(){
        try {
            String s = new ByteBuddy()
                    .subclass(Object.class)
                    .name("example.Type")
                    .make()
                    .load(this.getClass().getClassLoader())
                    .getLoaded()
                    .newInstance()
                    .toString();
            System.out.println(s);
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

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
