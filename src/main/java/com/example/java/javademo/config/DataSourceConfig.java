package com.example.java.javademo.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Component
@PropertySource(value = "classpath:jdbc.property")
public class DataSourceConfig {
    @Value("${driverClassName}")
    private String driverClassName;

    @Value("${username}")
    private String username;

    @Value("${password}")
    private String password;

    @Value("${dataSourceUrl}")
    private String dataSourceUrl;


    public void setDriverClassName(String driverClassName){
        this.driverClassName = driverClassName;
    }
    public String getDriverClassName(){
        return this.driverClassName;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password){
        this.password = password;
    }

    public String getPassword(){
        return this.password;
    }

    @Override
    public String toString() {
        return "{driverClassName:"+this.driverClassName+"}";
    }
}
