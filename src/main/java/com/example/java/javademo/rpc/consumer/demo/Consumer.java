package com.example.java.javademo.rpc.consumer.demo;

import com.example.java.javademo.rpc.provider.demo.HelloService;

public class Consumer {
    public static void main(String[] args) {
        RpcClientProxy rpcClientProxy = new RpcClientProxy();
        HelloService helloService = rpcClientProxy.clientProxy(HelloService.class, "localhost", 8080);
        System.out.println(helloService.sayHello("mic"));
    }
}
