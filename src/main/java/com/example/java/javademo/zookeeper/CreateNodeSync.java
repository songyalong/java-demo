package com.example.java.javademo.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Author: songyalong
 * @Description: 同步创建节点
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class CreateNodeSync implements Watcher {
    static  ZooKeeper zooKeeper;
    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.137.2:2181,192.168.137.3:2181,192.168.137.4:2181,", 5000, new CreateNodeSync());
        Thread.sleep(Integer.MAX_VALUE);
    }
    // 同步创建节点
    private void doSomeThing() throws KeeperException, InterruptedException {
        String path = zooKeeper.create("/znode_1", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        System.out.println("返回数据="+path);
    }
    @Override
    public void process(WatchedEvent watchedEvent) {

        // 如果是连接状态
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            try {
                doSomeThing();
            } catch (KeeperException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }
}
