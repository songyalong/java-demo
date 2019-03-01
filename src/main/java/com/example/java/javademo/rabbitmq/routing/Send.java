package com.example.java.javademo.rabbitmq.routing;

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
public class Send {
    private static final String EXCHANGE_NAME = "test_exchange_direct";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明exchange
        channel.exchangeDeclare(EXCHANGE_NAME, "direct");
        channel.basicQos(1);
        String message = "hello direct";

        // 发送消息
        String routingKey = "warning";
        channel.basicPublish(EXCHANGE_NAME, routingKey, null, message.getBytes());
        System.out.println("send " +  message);
        channel.close();
        connection.close();

    }
}
