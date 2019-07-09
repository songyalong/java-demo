package com.example.java.javademo.rpc.provider.demo;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.Socket;

public class ExportTask implements Runnable {

    public ExportTask(Socket socket) {
        this.socket = socket;
    }

    private Socket socket = null;



    @Override
    public void run() {
        ObjectInputStream objectInputStream = null;
        ObjectOutputStream objectOutputStream = null;

        try {
            objectInputStream = new ObjectInputStream(socket.getInputStream());
            String methodName = objectInputStream.readUTF();
            String interfaceName = objectInputStream.readUTF();
            Class<?> server = Class.forName(interfaceName);
            Class<?>[] parameterTypes = (Class<?>[]) objectInputStream.readObject();
            Method method = server.getMethod(methodName, parameterTypes);
//            method.invoke(server.newInstance(), parameterTypes);


        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }


    }
}
