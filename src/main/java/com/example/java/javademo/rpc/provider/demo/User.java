package com.example.java.javademo.rpc.provider.demo;

import java.io.Serializable;

public class User implements Serializable {

    private static final long serialVersionUID = -120704589545254425L;

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
