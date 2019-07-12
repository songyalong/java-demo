package com.example.java.javademo.dubbo.spi;

import com.alibaba.dubbo.common.extension.ExtensionLoader;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class Test {
    public static void main(String[] args) {
        ExtensionLoader<DubboService> extensionLoader = ExtensionLoader.getExtensionLoader(DubboService.class);
        DubboService aDubboService = extensionLoader.getExtension("aDubboService");
        aDubboService.sayHi();

        DubboService bDubboService = extensionLoader.getExtension("bDubboService");
        bDubboService.sayHi();
    }
}
