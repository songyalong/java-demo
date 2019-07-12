package com.example.java.javademo.rpc.provider.demo;

public class EchoServiceImpl implements EchoService {
    @Override
    public String echo(String ping) {
        return null != ping? ping+" I am OK" : "I am OK";
    }
}
