package com.example.java.javademo.rpc.provider.demo;

public class HelloServiceImpl implements HelloService {
    @Override
    public String saveUser(User user) {
        System.out.println("user-> "+ user);
        return "success";
    }

    @Override
    public String sayHello(String result) {
        return "hello world "+ result;
    }
}
