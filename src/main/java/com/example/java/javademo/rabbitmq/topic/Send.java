package com.example.java.javademo.rabbitmq.topic;

import com.example.java.javademo.rabbitmq.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: songyalong
 * @Description: topic 主体模式，发送者
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Send {
    private static final String EXCHANGE_NAME = "test_exchange_topic";

    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.exchangeDeclare(EXCHANGE_NAME, "topic");

        String routingKey = "goods.update";
        String message = "商品。。。。";

        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());

        channel.close();
        connection.close();
    }
}
