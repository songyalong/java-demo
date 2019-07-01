package com.example.java.javademo.rabbitmq.ps;

import com.example.java.javademo.rabbitmq.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: songyalong
 * @Description: 发布订阅模式
 * 一个生产者多个消费者
 * 每个消费者都有自己的队列
 * 生产者将消息发送到交换机，而不是队列上
 *消息发送到没有队列绑定的交换机时，消息将丢失，
 * 因为，交换机没有存储消息的能力，消息只能存在在队列中
 * 同一个消息被多个消费者获取，
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
        for (int i = 0; i< 100; i++){
            String message = "hello world"+ i;
            channel.basicPublish(EXCHANGE_NAME, "", null, message.getBytes());
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }


        channel.close();
        connection.close();

    }
}
