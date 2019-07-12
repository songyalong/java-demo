package com.example.java.javademo.rpc.provider.demo;

import java.util.concurrent.ConcurrentHashMap;

public class Provider {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();

        RpcServerProxy rpcServerProxy = new RpcServerProxy();
        rpcServerProxy.publisher(helloService, 8080);

    }
}
