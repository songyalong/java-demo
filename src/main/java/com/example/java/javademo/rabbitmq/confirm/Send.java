package com.example.java.javademo.rabbitmq.confirm;

import com.example.java.javademo.rabbitmq.util.RabbitmqConnectionUtil;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * @Author: songyalong
 * @Description: confirm 单个发 发送者
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Send {
    private static final String QUEUE_NAME = "test_queue_confirm_1";
    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 开启confirm模式，一个队列只能有一种模式
        channel.confirmSelect();

        String message = "send confirm...";
        channel.basicPublish("", QUEUE_NAME, null , message.getBytes());

        if(!channel.waitForConfirms()){
            System.out.println("confirm send failed");
        }else{
            System.out.println("confirm send ok ");
        }

        // 关闭信道和连接
        channel.close();
        connection.close();

    }
}
