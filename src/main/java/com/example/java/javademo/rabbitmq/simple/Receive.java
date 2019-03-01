package com.example.java.javademo.rabbitmq.simple;

import com.example.java.javademo.rabbitmq.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Receive {
    private static final String QUEUE_NAME = "test_queue";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = RabbitmqConnectionUtil.getConnection();

        // 创建通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义消费者
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println(message);
            }
        };
        // 绑定通道和消费者
        channel.basicConsume(QUEUE_NAME, false, consumer);
    }

    private static void oldApi() throws IOException, TimeoutException, InterruptedException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        // 从连接中创建通道
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 定义消费者
        QueueingConsumer consumer = new QueueingConsumer(channel);

        // 监听队列
        channel.basicConsume(QUEUE_NAME, false, consumer);

        while (true){
            QueueingConsumer.Delivery delivery = consumer.nextDelivery();
            String message = new String(delivery.getBody());
            System.out.println("[Rec]:"+ message);
        }
    }
}
