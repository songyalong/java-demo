package com.example.java.javademo.spring.event;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @Author: songyalong
 * @Description: 邮件事件广播器
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class MailSend implements ApplicationContextAware {
    private ApplicationContext applicationContext;
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public void sendMail(String to){
        System.out.println("模拟发送邮件");
        MailSendEvent mailSendEvent = new MailSendEvent(this.applicationContext, to);
        this.applicationContext.publishEvent(mailSendEvent);
    }
}
