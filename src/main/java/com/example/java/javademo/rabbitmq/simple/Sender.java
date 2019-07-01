package com.example.java.javademo.rabbitmq.simple;

import com.example.java.javademo.rabbitmq.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: songyalong
 * @Description: 简单队列
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Sender {
    public static final String QUEUE_NAME = "test_queue";
    public static void main(String[] args) throws IOException, TimeoutException {

        int i =0;
        while(i < 30 ){
            // 获取rabbitmq 服务器连接
            Connection connection = RabbitmqConnectionUtil.getConnection();

            // 创建rabbitmq的通道
            Channel channel = connection.createChannel();
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);

            // 将消息发布到队列
            String message = "hello, world";
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println("消息已发送");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            i++;
        }
        // 获取rabbitmq 服务器连接
        Connection connection = RabbitmqConnectionUtil.getConnection();

        // 创建rabbitmq的通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 将消息发布到队列
        String message = "hello, world";
        channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
        System.out.println("消息已发送");
        // 关闭通道和连接
        channel.close();
        connection.close();

    }
}
