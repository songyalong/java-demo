package com.example.java.javademo.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Order(1)
@WebFilter(urlPatterns = "/*")
@Slf4j
@Component
public class TestFilterFirst implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        System.out.println("first filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
       log.info("过滤器1");
        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        System.out.println("url = "+ httpServletRequest.getRequestURI());
        filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        System.out.println("destroy");
    }
}
