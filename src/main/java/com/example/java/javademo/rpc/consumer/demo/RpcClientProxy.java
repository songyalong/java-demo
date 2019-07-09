package com.example.java.javademo.rpc.consumer.demo;

import java.lang.reflect.Proxy;

public class RpcClientProxy {
    public <T> T clientProxy(Class<T> interfaceClass, String host, int port){
        return (T) Proxy.newProxyInstance(interfaceClass.getClassLoader(), new Class<?>[]{interfaceClass}, new RemoteInvocationHandler(host, port));

    }
}
