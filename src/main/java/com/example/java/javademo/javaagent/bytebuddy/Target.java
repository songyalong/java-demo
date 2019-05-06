package com.example.java.javademo.javaagent.bytebuddy;

class Target {
  public static String hello(String name) {
    return "Hello " + name + "!";
  }
}