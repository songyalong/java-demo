package com.example.java.javademo.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CountDownLatch;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestCurator {
    final static String localPath = "/znode_1";
//    static CuratorFramework client = CuratorFrameworkFactory.builder().connectString("127.0.0.1:2181").retryPolicy(new ExponentialBackoffRetry(1000, 3)).build();
    static CuratorFramework client = CuratorFrameworkFactory.newClient("192.168.137.2:2181,192.168.137.3:2181,192.168.137.4:2181", new ExponentialBackoffRetry(1000, 3));
    public static void main(String[] args) {
        // 创建zk客户端
        client.start();
        InterProcessMutex interProcessMutex = new InterProcessMutex(client, localPath);
        final CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 100; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        countDownLatch.await();
                        interProcessMutex.acquire();
                    }catch (Exception e){

                    }

                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss|SSS");
                    String format = simpleDateFormat.format(new Date());
                    System.out.println("生成订单号："+format);

                    try{
                        interProcessMutex.release();
                    }catch (Exception e){

                    }

                }
            }).start();
        }


    }
}
