package com.example.java.javademo.classloader;

import com.alibaba.druid.pool.DruidDataSource;
import com.example.java.javademo.pojo.Book;
import com.example.java.javademo.pojo.Boss;
import com.example.java.javademo.spring.event.MailSend;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class ApplicationContextTest {

    @Test
    public void getBean(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Book book = (Book) applicationContext.getBean("book");
        System.out.println(book.getName());
    }

    @Test
    public void annotationApplicationContextTest(){
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(Book.class);
        Book book = (Book) applicationContext.getBean("book2");
        System.out.println(book.getName());
    }

    @Test
    public void factoryBeanTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Book book = (Book) applicationContext.getBean("book3");
        System.out.println(book.getName());
    }

    @Test
    public void propertyEditorTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        Boss book = (Boss) applicationContext.getBean("boss");
        System.out.println(book.getName());
        System.out.println(book.getCar());
    }

    @Test
    public void propertyPlaceHolderConfigurerTest(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        DruidDataSource dataSource = (DruidDataSource) applicationContext.getBean("dataSource");
        System.out.println(dataSource.getUrl());
    }

    @Test
    public void testSpringEvent(){
        ApplicationContext applicationContext = new ClassPathXmlApplicationContext("beans.xml");
        MailSend mailSend = (MailSend) applicationContext.getBean("mailSend");
        mailSend.sendMail("aaa@bbb.com");
    }
}
