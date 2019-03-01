package com.example.java.javademo.mybatis.outer;

import com.example.java.javademo.pojo.User;
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
public interface UserMapper {
    @Select("select * from user where id = #{id}")
    User getById(@Param("id") Integer id);
}
