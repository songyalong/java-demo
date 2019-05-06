package com.example.java.javademo.spring.event;

import org.springframework.context.ApplicationListener;

/**
 * @Author: songyalong
 * @Description: 邮件监听器
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class MaiSendListener implements ApplicationListener<MailSendEvent> {
    @Override
    public void onApplicationEvent(MailSendEvent mailSendEvent) {
        System.out.println("MailSendListener : 向"+ mailSendEvent.getTo() + "发送邮件");
    }
}
