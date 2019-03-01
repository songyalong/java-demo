package com.example.java.javademo.config;


import org.springframework.context.ApplicationContext;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public abstract class BeanProvider {
    private static ApplicationContext applicationContext;
    protected BeanProvider() {
    }
    public static void initialize(ApplicationContext applicationContext) {
        BeanProvider.applicationContext = applicationContext;
    }
    public static <T> T getBean(Class<T> clazz) {
        if (applicationContext == null) {
            return null;
        }
        return applicationContext.getBean(clazz);
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String beanId) {
        if (applicationContext == null) {
            return null;
        }
        return (T) applicationContext.getBean(beanId);
    }
}
