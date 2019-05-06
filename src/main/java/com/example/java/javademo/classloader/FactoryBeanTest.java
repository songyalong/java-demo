package com.example.java.javademo.classloader;

import com.example.java.javademo.pojo.Book;
import org.springframework.beans.factory.FactoryBean;

/**
 * @Author: songyalong
 * @Description: FactoryBean测试类
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class FactoryBeanTest implements FactoryBean {

    private String bookInfo;
    @Override
    public Object getObject() throws Exception {
        Book book = new Book();
        String[] split = bookInfo.split(",");
        book.setId(Integer.parseInt(split[0]));
        book.setName(split[1]);
        return book;
    }

    @Override
    public Class<?> getObjectType() {
        return Book.class;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }

    public void setBookInfo(String bookInfo) {
        this.bookInfo = bookInfo;
    }
}
