package com.example.java.javademo.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @Author: songyalong
 * @Description: 拦截器
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Slf4j
public class FirstInterceptor implements HandlerInterceptor{

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.info("[{}]执行{}方法", this.getClass().getSimpleName(), "preHandler");
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) {
        log.info("[{}]执行{}方法", this.getClass().getSimpleName(), "postHandler");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {
        log.info("[{}执行{}方法]",  this.getClass().getSimpleName(), "afterComplete");
    }
}
