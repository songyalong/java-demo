package com.example.java.javademo.rabbitmq.util;

import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: songyalong
 * @Description: 连接rabbitmq服务器
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class RabbitmqConnectionUtil {
    public static Connection getConnection() throws IOException, TimeoutException {
        // 连接工厂
        ConnectionFactory connectionFactory = new ConnectionFactory();
        // 服务器地址
        connectionFactory.setHost("localhost");
        //服务器端口
        connectionFactory.setPort(5672);
        // rabbitmq用户名
        connectionFactory.setUsername("guest");
        // rabbitmq密码
        connectionFactory.setPassword("guest");
        return connectionFactory.newConnection();
    }
}
