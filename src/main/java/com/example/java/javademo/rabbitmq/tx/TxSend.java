package com.example.java.javademo.rabbitmq.tx;

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
public class TxSend {
    private static final String QUEUE_NAME = "test_queue_tx";
    public static void main(String[] args) throws IOException, TimeoutException {
        Connection connection = RabbitmqConnectionUtil.getConnection();
        Channel channel = connection.createChannel();
        channel.queueDeclare(QUEUE_NAME, false, false, false, null);

        // 发送数据
        String message  = "send  tx message";
        try{
            channel.txSelect();
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            int tt = 1/0;
            channel.txCommit();
        }catch (Exception e){
            channel.txRollback();
            System.out.println("发送消息失败");
        }
        channel.close();
        connection.close();
    }
}
