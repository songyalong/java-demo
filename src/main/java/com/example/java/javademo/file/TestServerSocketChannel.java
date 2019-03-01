package com.example.java.javademo.file;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * @Author: songyalong
 * @Description: nio test serversocketchannel
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestServerSocketChannel {
    public static void main(String[] args) throws IOException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.socket().bind(new InetSocketAddress(9999));
        // 非阻塞
        serverSocketChannel.configureBlocking(false);
        while (true){
            SocketChannel socketChannel = serverSocketChannel.accept();
            if(null != socketChannel && socketChannel.finishConnect()){
                ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
                int read = socketChannel.read(byteBuffer);
                while(read != -1){
                    out(byteBuffer);
                    byteBuffer.clear();
                    read = socketChannel.read(byteBuffer);
                }
            }else{
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }

    private static void out(ByteBuffer byteBuffer) {
        byteBuffer.flip();
        StringBuffer sb = new StringBuffer();
        if(byteBuffer.hasRemaining()){
           for(int i=0; i<byteBuffer.limit(); i++){
               sb.append(new char[]{(char)byteBuffer.get()});
           }
            System.out.println(sb.toString());
        }
    }
}
