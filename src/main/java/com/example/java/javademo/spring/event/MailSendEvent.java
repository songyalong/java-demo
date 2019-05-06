package com.example.java.javademo.spring.event;

import org.springframework.context.ApplicationContext;
import org.springframework.context.event.ApplicationContextEvent;

/**
 * @Author: songyalong
 * @Description: 发送邮件事件
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class MailSendEvent extends ApplicationContextEvent {
    private String to;
    public MailSendEvent(ApplicationContext source,String to) {
        super(source);
        this.to = to;
    }

    public String getTo(){
        return this.to;
    }
}
