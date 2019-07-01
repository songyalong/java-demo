package com.example.java.javademo.zookeeper;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;

/**
 * @Author: songyalong
 * @Description: 连接 zookeeper
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class CreateSession implements Watcher {
    public static void main(String[] args) throws IOException, InterruptedException {
        ZooKeeper zooKeeper = new ZooKeeper("192.168.137.2:2181,192.168.137.3:2181,192.168.137.4:2181,", 5000, new CreateSession());
//        Thread.sleep(Integer.MAX_VALUE);
        System.out.println(zooKeeper.getState());
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        System.out.println("收到的事件="+watchedEvent);
    }
}
