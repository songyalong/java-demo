package com.example.java.javademo.mybatis.primary;

import com.example.java.javademo.pojo.FormData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CachePut;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Mapper
@CacheConfig(cacheNames = "formData")
public interface FormDataMapper {
    @Select("select * from formdata where id = #{id}")
    @CachePut(key = "#p0")
    FormData getById(@Param("id") Integer id);
}
