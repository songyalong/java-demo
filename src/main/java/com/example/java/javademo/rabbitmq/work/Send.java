package com.example.java.javademo.rabbitmq.work;

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
    public static final String QUEUE_NAME = "test_queue";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        
        // 创建channal
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);
        
        //发布信息
        for (int i = 0; i < 50; i++) {
            String message = "hello "+i;
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
