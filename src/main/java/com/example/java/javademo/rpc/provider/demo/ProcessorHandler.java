package com.example.java.javademo.rpc.provider.demo;



import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

public class ProcessorHandler implements  Runnable {

    Socket socket;
    Object service;

    ProcessorHandler(Socket socket, Object service){
        this.socket = socket;
        this.service = service;
    }

    @Override
    public void run() {
        System.out.println("开始处理客户端请求");
        ObjectInputStream objectInputStream = null;
        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object invoke = invoke(rpcRequest);
            // 结果写回去
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(invoke);
            objectOutputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Object invoke(RpcRequest rpcRequest){
        Object[] parameters = rpcRequest.getParameters();
        Class<?>[] types = new Class[parameters.length];
        for (int i = 0; i < parameters.length; i++) {
            types[i] = parameters[i].getClass();
        }

        try {
            Method method = service.getClass().getMethod(rpcRequest.getMethodName(), types);
            Object object = method.invoke(service, parameters);
            return object;
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;

    }
}
