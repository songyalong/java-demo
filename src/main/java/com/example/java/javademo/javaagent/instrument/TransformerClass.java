package com.example.java.javademo.javaagent.instrument;

import org.apache.ibatis.javassist.*;

import java.io.IOException;
import java.lang.instrument.ClassFileTransformer;
import java.lang.instrument.IllegalClassFormatException;
import java.security.ProtectionDomain;

/**
 * @Author: songyalong
 * @Description: 转换类
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TransformerClass implements ClassFileTransformer {
    /**
     *
     * @param loader 定义要转换的类加载器
     * @param className 类的接口名称
     * @param classBeingRedefined   如果是被重定义或重转换触发
     * @param protectionDomain  要定义或重定义的类的保护域
     * @param classfileBuffer   类文件格式的输入字节缓冲区
     * @return
     * @throws IllegalClassFormatException
     */
    @Override
    public byte[] transform(ClassLoader loader, String className, Class<?> classBeingRedefined, ProtectionDomain protectionDomain, byte[] classfileBuffer) throws IllegalClassFormatException {

        if(className.equals("com/example/java/javademo/javaagent/instrument/TransClass")){
            ClassPool classPool = ClassPool.getDefault();

            try {
                CtClass ctClass = classPool.get(className.replaceAll("/", "."));
                CtMethod sayHello = ctClass.getDeclaredMethod("sayHello");
                if(!sayHello.isEmpty()){
                    sayHello.insertBefore("System.out.println(\"before hello!!!\");");
                    sayHello.insertAfter("System.out.println(\"after hello!!!\");");
                }
                return ctClass.toBytecode();
            } catch (NotFoundException e) {
                e.printStackTrace();
            } catch (CannotCompileException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }


        return new byte[0];
    }
}
