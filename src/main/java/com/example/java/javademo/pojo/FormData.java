package com.example.java.javademo.pojo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Getter
@Setter
@ToString
public class FormData implements Serializable {
    private String department;
    private String content;
}
