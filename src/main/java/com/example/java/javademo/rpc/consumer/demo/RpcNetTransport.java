package com.example.java.javademo.rpc.consumer.demo;

import com.example.java.javademo.rpc.provider.demo.RpcRequest;

import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class RpcNetTransport {
    private String host;
    private int port;

    public RpcNetTransport(String host, int port) {
        this.host = host;
        this.port = port;
    }
    private Socket newSocket() {
        System.out.println("创建一个socket连接");
        Socket socket = null;
        try {
            socket = new Socket(host, port);
        } catch (IOException e) {
            System.out.println("建立连接失败");
            e.printStackTrace();
        }
        return socket;
    }

    public Object sendRequest(RpcRequest rpcRequest){
        Socket socket = null;
        socket = newSocket();
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            objectOutputStream.flush();

            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            Object object = objectInputStream.readObject();
            objectInputStream.close();
            objectOutputStream.close();
            return object;

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }finally {
            if(socket != null){
                try {
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        return null;

    }
}
