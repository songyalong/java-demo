package com.example.java.javademo.config;

import com.example.java.javademo.filter.TestFilterFirst;
import org.springframework.boot.web.servlet.DelegatingFilterProxyRegistrationBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import java.io.IOException;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Configuration
public class FilterConfiguration {

    @Bean
    public FilterRegistrationBean filterRegistrationBean111(){
        FilterRegistrationBean filterRegistrationBean111 = new FilterRegistrationBean();
        filterRegistrationBean111.setFilter(new TestFilterFirst());
        filterRegistrationBean111.setOrder(1);
        filterRegistrationBean111.addUrlPatterns("/*");
        return filterRegistrationBean111;
    }

    @Bean("proxyFilter")
    public Filter filter(){
        return new Filter(){
            @Override
            public void init(FilterConfig filterConfig) {
                System.out.println("init........");
            }

            @Override
            public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
                System.out.println("doFilter......");
                filterChain.doFilter(servletRequest, servletResponse);
            }

            @Override
            public void destroy() {
                System.out.println("destroy......");
            }
        };
    }

    @Bean
    public DelegatingFilterProxyRegistrationBean delegatingFilterProxyRegistrationBean(){
        DelegatingFilterProxyRegistrationBean bean = new DelegatingFilterProxyRegistrationBean("proxyFilter");
        bean.addUrlPatterns("/*");
        return bean;
    }
}
