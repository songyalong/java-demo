package com.example.java.javademo.rabbitmq.work;

import com.example.java.javademo.rabbitmq.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: songyalong
 * @Description: work 模式
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Send {
    public static final String QUEUE_NAME = "test_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        
        // 创建channal
        Channel channel = connection.createChannel();
        /**
         * queueDecalre 有5个残次数
         * 参数一：queue 队列名称
         * 参数二：durable 持久化
         * 参数三： exculsive 是否排除其他， true 排他， 如果一个队列声明为排他的队列，
         * 参数四： autoDelete 是否自动删除，无其他客户端连接队列，将删除
         * 参数五： 其他参数
         */
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        //发布信息
        for (int i = 0; i < 100; i++) {
            String message = "hello "+i;
            /**
             * basicPulbic是重载方法
             * 参数一： exchange：交换机名称
             * 参数二： routingKey： 路由键
             * 参数三： BasicProperties 消息基本属性，
             * 参数四： 消息体
             */
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());

            try {
                Thread.sleep(i*100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }

        channel.close();
        connection.close();
        


    }
}
