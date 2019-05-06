package com.example.java.javademo.javaagent.bytebuddy;

public interface Second {
  default String qux() { return "BAR"; }
}