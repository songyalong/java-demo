package com.example.java.javademo.rabbitmq.ps;

import com.example.java.javademo.rabbitmq.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: songyalong
 * @Description: 发布订阅模式
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Send {
    public static final String EXCHANGE_NAME = "test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();

        // 声明交换机
        channel.exchangeDeclare(EXCHANGE_NAME, "fanout");

        // 发送消息
        String message = "hello world";
        channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());

        channel.close();
        connection.close();

    }
}
