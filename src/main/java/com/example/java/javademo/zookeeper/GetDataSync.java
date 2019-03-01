package com.example.java.javademo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @Author: songyalong
 * @Description: 同步获取节点数据
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class GetDataSync implements Watcher {
    static  ZooKeeper zooKeeper;
    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.137.2:2181,192.168.137.3:2181,192.168.137.4:2181,", 5000, new GetDataSync());
        Thread.sleep(Integer.MAX_VALUE);
    }
    // 同步创建节点
    private void doSomeThing() {
        Stat state = new Stat();
        try {
            byte[] data = zooKeeper.getData("/znode_1", true, state);
            System.out.println(new String(data));
        } catch (KeeperException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void process(WatchedEvent watchedEvent) {

        // 如果是连接状态
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            doSomeThing();
        }

    }
}
