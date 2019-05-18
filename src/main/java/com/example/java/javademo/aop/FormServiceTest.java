package com.example.java.javademo.aop;

import org.junit.Test;

import java.lang.reflect.Proxy;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class FormServiceTest {

    @Test
    public void jdkProxy(){
        ForumService forumService = new ForumServiceImpl();
        PerformanceHandler performanceHandler = new PerformanceHandler(forumService);
        ForumService proxy = (ForumService) Proxy.newProxyInstance(forumService.getClass().getClassLoader(),
                forumService.getClass().getInterfaces(), performanceHandler);
        System.out.println(proxy.getClass().getName());
        proxy.removeForum(123);
        proxy.removeTopic(456);
    }

    @Test
    public void cglibProxy(){
        CglibProxy cglibProxy = new CglibProxy();
        ForumServiceImpl forumServiceImpl = (ForumServiceImpl) cglibProxy.getProxy(ForumServiceImpl.class);
        forumServiceImpl.removeForum(123);
        forumServiceImpl.removeTopic(456);
    }
}
