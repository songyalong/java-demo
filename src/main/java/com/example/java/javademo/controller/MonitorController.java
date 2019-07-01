package com.example.java.javademo.controller;

import com.example.java.javademo.mybatis.primary.FormDataMapper;
import com.example.java.javademo.pojo.Book;
import com.example.java.javademo.pojo.FormData;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Api(description = "用户接口")
@RestController
@Slf4j
public class MonitorController {
    @Autowired
    private FormDataMapper formDataMapper;

    @ModelAttribute
    public void test(){
        System.out.println("tttt");
    }

    @ApiOperation(value = "测试", notes = "根据用户来测试")
    @RequestMapping(value="/createUser",method= RequestMethod.POST,consumes= MediaType.APPLICATION_JSON_VALUE)
    public Map<String,Object> hello(@RequestBody Book book){
        System.out.println(book.getId());
        System.out.println("ttttttttttttt");
        Map<String, Object> data = new HashMap<>();
        data.put("hello", "world");
        data.put("book", book);
        return data;
    }

    @ApiOperation(value = "测试", notes = "根据用户来测试")
    @RequestMapping(value="/formdata",method= RequestMethod.POST)
    public Object hello(){
        log.info("log debug");
        FormData formData = formDataMapper.getById(8926);
        System.out.println(formData);
        return formData;
    }



}
