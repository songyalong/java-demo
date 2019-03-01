package com.example.java.javademo.mybatis.primary;

import com.example.java.javademo.pojo.FormData;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Mapper
public interface FormDataMapper {
    @Select("select * from formdata where id = #{id}")
    FormData getById(@Param("id") Integer id);
}
