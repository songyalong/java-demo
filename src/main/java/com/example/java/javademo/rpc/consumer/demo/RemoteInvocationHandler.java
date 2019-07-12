package com.example.java.javademo.rpc.consumer.demo;

import com.example.java.javademo.rpc.provider.demo.RpcRequest;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class RemoteInvocationHandler implements InvocationHandler {
    private String host;
    private int port;

    public RemoteInvocationHandler(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

        RpcRequest rpcRequest = new RpcRequest();
        rpcRequest.setMethodName(method.getName());
        rpcRequest.setParameters(args);
        RpcNetTransport rpcNetTransport = new RpcNetTransport(host, port);
        return  rpcNetTransport.sendRequest(rpcRequest);
    }
}
