package com.example.java.javademo.dubbo.spi;

import com.alibaba.dubbo.common.extension.SPI;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@SPI
public interface DubboService {
    public void sayHi();
}
