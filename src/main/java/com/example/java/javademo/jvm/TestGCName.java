package com.example.java.javademo.jvm;

import java.lang.management.GarbageCollectorMXBean;
import java.lang.management.ManagementFactory;
import java.util.List;

/**
 * @Author: songyalong
 * @Description: 获取 垃圾收集器名称
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestGCName {
    public static void main(String[] args) {
        List<GarbageCollectorMXBean> garbageCollectorMXBeans = ManagementFactory.getGarbageCollectorMXBeans();
        for (GarbageCollectorMXBean garbageCollectorMXBean : garbageCollectorMXBeans){
            System.out.println(garbageCollectorMXBean.getName());
        }
    }
}
