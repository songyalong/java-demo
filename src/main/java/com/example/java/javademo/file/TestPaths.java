package com.example.java.javademo.file;

import java.net.URISyntaxException;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * @Author: songyalong
 * @Description: java nio path
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestPaths {
    public static void main(String[] args) {
        TestPaths testPaths = new TestPaths();
        testPaths.testPathsResove();
    }

    public  void testPathsResove(){
        try {
            Path path = Paths.get(this.getClass().getClassLoader().getResource("application.properties").toURI());
            Path resolve = path.resolve(path);
            // print D:\idea\java-demo\target\classes\application.properties
            System.out.println(resolve.toString());
            //print application.properties
            System.out.println(resolve.getFileName());

            // print D:\idea\java-demo\target\classes
            System.out.println(resolve.getParent().toString());

            // print  d:/
            System.out.println(resolve.getRoot());


        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
