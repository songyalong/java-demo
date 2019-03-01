package com.example.java.javademo.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class CustomApplicationContextAware implements ApplicationContextAware {

    public CustomApplicationContextAware(){}

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        BeanProvider.initialize(applicationContext);
    }
}
