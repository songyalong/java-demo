package com.example.java.javademo.nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
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
    public static void main(String[] args) throws IOException {
        test();
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

    public static void test() throws IOException {
        RandomAccessFile file = new RandomAccessFile("D:\\idea\\java-demo\\src\\main\\java\\com\\example\\java\\javademo\\nio\\test", "rw");
        FileChannel fileChannel = file.getChannel();
        ByteBuffer allocate = ByteBuffer.allocate(48);
        int read = fileChannel.read(allocate);
        while(read != -1){
            System.out.println("Read " + read);
            allocate.flip();
            while (allocate.hasRemaining()){
                System.out.println((char) allocate.get());
            }
            allocate.clear();
            read = fileChannel.read(allocate);
        }
        fileChannel.close();
        file.close();
    }


    /**
     * 测试selector
     */
    public static void testSelector() throws IOException {

    }

    /**
     * 文件
     */
    public static void testFileChannel() throws FileNotFoundException {
        FileInputStream fileInputStream = new FileInputStream("");
        FileChannel channel = fileInputStream.getChannel();

        // 创建缓冲区， 数据的读写存放在缓冲区
        ByteBuffer.allocate(48);

    }










}
