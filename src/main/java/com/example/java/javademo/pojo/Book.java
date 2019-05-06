package com.example.java.javademo.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.Serializable;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Configuration
public class Book implements Serializable {
    private Integer id;
    private String name;
    public Book(){}
    public Book(Integer id, String name){
        this.id = id;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Bean(name = "book2")
    public Book book(){
        Book book = new Book();
        book.setId(123);
        book.setName("Spring 核心笔记");
        return book;
    }
}
