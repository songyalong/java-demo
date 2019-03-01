package com.example.java.javademo.rabbitmq.ps;

import com.example.java.javademo.rabbitmq.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: songyalong
 * @Description: 消费者1
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Receive2 {
    public static final String QUEUE_NAME = "test_queue_sms";
    public static final String EXCHANGE_NAME = "test_exchange_fanout";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        // 声明队列
        channel.queueDeclare(QUEUE_NAME, false, false, false,null);

        // 绑定交换机
        channel.queueBind(QUEUE_NAME, EXCHANGE_NAME, "");

        channel.basicQos(1);
        DefaultConsumer consumer = new DefaultConsumer(channel) {
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                String message = new String(body, "UTF-8");
                System.out.println("[2] reced " + message);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("done");
                }
                // 手动应答
                channel.basicAck(envelope.getDeliveryTag(), false);
            }
        };

        //关闭自动应答
        boolean ack = false;
        channel.basicConsume(QUEUE_NAME, ack, consumer);



    }
}
