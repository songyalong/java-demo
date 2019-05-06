package com.example.java.javademo.javaagent.bytebuddy;

public interface First {
  default String qux() { return "FOO"; }
}