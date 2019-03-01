package com.example.java.javademo.rabbitmq.simple;

import com.example.java.javademo.rabbitmq.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Sender {
    public static final String QUEUE_NAME = "test_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        // 获取rabbitmq 服务器连接
        Connection connection = RabbitmqConnectionUtil.getConnection();

        // 创建rabbitmq的通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 将消息发布到队列
        String message = "hello, world";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

        // 关闭
        channel.close();
        connection.close();

    }
}
