package com.example.java.javademo.file;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * @Author: songyalong
 * @Description: test nio socketchannel
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestSocketChannel {
    private static final String MSG = "hello";
    public static void main(String[] args){
        try {
            InetSocketAddress inetSocketAddress = new InetSocketAddress("127.0.0.1", 9999);
            SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
            socketChannel.configureBlocking(false);
            while (!socketChannel.finishConnect()){
                unConnect();
            }

            ByteBuffer byteBuffer = ByteBuffer.wrap(MSG.getBytes());
            socketChannel.write(byteBuffer);
            socketChannel.close();

        }catch (IOException e){
            e.printStackTrace();
        }
    }

    private static void unConnect() {
        System.out.println("do something useless!");
    }
}
