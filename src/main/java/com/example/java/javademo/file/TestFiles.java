package com.example.java.javademo.file;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.file.*;
import java.util.List;

import static java.nio.file.StandardCopyOption.*;

/**
 * @Author: songyalong
 * @Description: test java nio files
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestFiles {
    public static void main(String[] args) {
        TestFiles testFiles = new TestFiles();
//        testFiles.testReadAllBytes();
//        testFiles.testReadLine();
//        testFiles.testWrite();
//        testFiles.testCopy();
//        testFiles.testMove();
//        testFiles.deleteFile();
//        testFiles.testNewDirStream();
        testFiles.useNIO();
    }

    public Path getFileURI(){
        try {
            return Paths.get(this.getClass().getClassLoader().getResource("application.properties").toURI());
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void testReadAllBytes(){
        try {
            byte[] bytes = Files.readAllBytes(getFileURI());
            String fileContent = new String(bytes, "UTF-8");
            System.out.println(fileContent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void testReadLine(){
        try {
            List<String> strings = Files.readAllLines(getFileURI());
            strings.forEach(str-> System.out.println(str));
            strings.stream().forEach(str -> System.out.println(str));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // test write
    public void testWrite(){
        try {
            String content = "spring.name=hello";
            Path path = Files.write(getFileURI(), content.getBytes());
            System.out.println(path.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // test copy
    public  void testCopy(){
        try {
            Files.copy(getFileURI(), Paths.get("D://test//application.properties"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // test move
    public void testMove(){
        try {
            Files.move(getFileURI(), Paths.get("D://test/application.properties"), StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // delete file
    public void deleteFile(){
        try {
            Files.deleteIfExists(Paths.get("D://test/application.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 迭代目录流
    public void testNewDirStream(){
        try {
            DirectoryStream<Path> paths = Files.newDirectoryStream(Paths.get("D://test/"));
            for(Path path : paths){
                System.out.println(path.toString());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void useNIO(){
        RandomAccessFile file = null;
        try {
            file = new RandomAccessFile("D:\\test\\application.properties", "rw");
            FileChannel channel = file.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(48);
            int read = channel.read(byteBuffer);
            while(read > -1){
                // 将界线设置当前位置，并把位置复位到0， 是缓冲区为读做好准备
                byteBuffer.flip();
                while(byteBuffer.hasRemaining()){
                    System.out.println((char) byteBuffer.get());
                }
                byteBuffer.clear();
                read = channel.read(byteBuffer);
            }
            channel.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try {
                file.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }



}
