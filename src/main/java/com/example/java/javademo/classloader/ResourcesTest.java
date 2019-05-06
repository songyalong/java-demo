package com.example.java.javademo.classloader;

import org.junit.Test;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.PathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.WritableResource;
import org.springframework.core.io.support.EncodedResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.core.io.support.ResourcePatternResolver;
import org.springframework.util.FileCopyUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class ResourcesTest {
    public static void main(String[] args) throws IOException {
    }

    @Test
    public  void resource() throws IOException {
        String filePath = "D:\\idea\\java-demo\\src\\main\\resources\\test1.properties";
        WritableResource writableResource = new PathResource(filePath);
        OutputStream outputStream = writableResource.getOutputStream();
        outputStream.write("欢迎光临".getBytes());
        outputStream.close();

        Resource resource = new ClassPathResource("test1.properties");

        InputStream inputStream = resource.getInputStream();

        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        int i;
        while ((i = inputStream.read()) != -1){
            byteArrayOutputStream.write(i);
        }
        System.out.println(byteArrayOutputStream.toString());
        System.out.println(writableResource.getFilename());
        System.out.println(resource.getFilename());
    }

    @Test
    public  void encodeResourceTest() throws IOException {
        Resource resource = new ClassPathResource("test1.properties");
        EncodedResource encodedResource = new EncodedResource(resource, "UTF-8");
        String copyToString = FileCopyUtils.copyToString(encodedResource.getReader());
        System.out.println(copyToString);
    }

    @Test
    public void patternResolverTest() throws IOException {
        ResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resourcePatternResolver.getResources("classpath: *.properties");


        for(Resource resource : resources){
            System.out.println(resource.getDescription());
        }
    }
}
