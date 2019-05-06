package com.example.java.javademo.spring;

import com.example.java.javademo.pojo.Car;
import org.springframework.util.StringUtils;

import java.beans.PropertyEditorSupport;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class CustomCarEditor extends PropertyEditorSupport {

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        if(StringUtils.isEmpty(text)){
            throw new IllegalArgumentException("设置参数格式错误");
        }
        Car car = new Car();
        String[] split = text.split(",");
        car.setBrand(split[0]);
        car.setMaxSpeed(Integer.valueOf(split[1]));
        car.setPrice(Double.valueOf(split[2]));
        super.setValue(car);
    }
}
