package com.example.java.javademo.classloader;

import com.example.java.javademo.pojo.Book;
import org.junit.Test;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;

import java.io.IOException;

/**
 * @Author: songyalong
 * @Description: BeanFactory 测试类
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class BeanFactoryTest {

    @Test
    public void getBean() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource resource = resourcePatternResolver.getResource("classpath:beans.xml");
        System.out.println(resource.getURL());
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader xmlBeanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        xmlBeanDefinitionReader.loadBeanDefinitions(resource);
        System.out.println("init bean factory");
        Book book = (Book) beanFactory.getBean("book");
        Book book2 = (Book) beanFactory.getBean("book");
        System.out.println(book == book2);
        System.out.println(book);
        System.out.println(book2);



    }
}
