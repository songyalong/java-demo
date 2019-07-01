package com.example.java.javademo.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@WebServlet(name = "firstServlet", urlPatterns = "/*")
public class FirstServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        System.out.println("request method ： "+ req.getMethod());
        resp.getWriter().append("firstServlet");
        super.doGet(req, resp);
    }
}
