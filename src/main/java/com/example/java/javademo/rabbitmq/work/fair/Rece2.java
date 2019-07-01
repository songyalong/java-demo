package com.example.java.javademo.rabbitmq.work.fair;

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
public class Rece2 {
    public static final String QUEUE_NAME = "test_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        // 同一时刻，服务器只会向消费者发送1条消息
        channel.basicQos(1);

        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("[2] reced " + message);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("done");
                }
                // 消费者自动确认
                /**
                 * 模式一： 自动确认， 只要消息从队列中取出，无论消费是否成功，都认为消息是成功消费的
                 * 模式二： 手动确认，消费者从队列中获取消息后，服务器会将消息设置为不可用状态，等待消费者的反馈，如果消费者一直未消费
                 * 该消息将会一直是不可用状态
                 */
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };
        boolean ack = false;
        // 监听队列， false：表示手动返回状态， true：表示自动
        channel.basicConsume(QUEUE_NAME, ack, consumer);
    }
}
