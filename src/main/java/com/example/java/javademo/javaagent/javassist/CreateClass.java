package com.example.java.javademo.javaagent.javassist;

import org.apache.ibatis.javassist.*;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过Javassist创建类
 */
public class CreateClass {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        ClassPool classPool  = ClassPool.getDefault();
        CtClass ctClass = classPool.makeClass("com.example.java.javademo.javaagent.javassist.Cclass");
        // 添加属性和方法
        CtField ctField = null;
        try {
            ctField = new CtField(classPool.get("java.lang.String"), "name", ctClass);
            ctField.setModifiers(Modifier.PRIVATE);
            //增加get，set方法
            ctClass.addMethod(CtNewMethod.setter("setName", ctField));
            ctClass.addMethod(CtNewMethod.getter("getName", ctField));
            ctClass.addField(ctField, CtField.Initializer.constant(""));
            // 增加无参构造函数
            CtConstructor ctConstructor = new CtConstructor(new CtClass[]{}, ctClass);
            ctConstructor.setBody("{name=\"Ben\";}");
            ctClass.addConstructor(ctConstructor);
            // 添加有参构造方法
            ctConstructor = new CtConstructor(new CtClass[]{classPool.get("java.lang.String")}, ctClass);
            ctConstructor.setBody("{$0.name = $1;}");
            ctClass.addConstructor(ctConstructor);
            // 生成的类写入文件
            byte[] bytes = ctClass.toBytecode();
            FileOutputStream fileOutputStream = new FileOutputStream(new File("f://Emp.class"));
            fileOutputStream.write(bytes);
            fileOutputStream.close();
        } catch (CannotCompileException | IOException e) {
            e.printStackTrace();
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        try {
            Object o = Class.forName("com.example.java.javademo.javaagent.javassist.Cclass").newInstance();
            Method getName = o.getClass().getMethod("getName");
            System.out.println(getName.invoke(o));
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
            e.printStackTrace();
        }



    }
}
