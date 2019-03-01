package com.example.java.javademo.nio;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

/**
 * @Author: songyalong
 * @Description: test nio files
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestFiles {
    public static void main(String[] args) {
        TestFiles testFiles = new TestFiles();
        testFiles.testCopy();
    }

    public void testCopy(){
        Path src = Paths.get("D://test/");
        Path dest = Paths.get("E://");

        try {
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
