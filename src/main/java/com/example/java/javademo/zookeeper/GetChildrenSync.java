package com.example.java.javademo.zookeeper;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

import java.io.IOException;
import java.util.List;

/**
 * @Author: songyalong
 * @Description: 同步获取子节点
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class GetChildrenSync implements Watcher {
    static  ZooKeeper zooKeeper;
    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.137.2:2181,192.168.137.3:2181,192.168.137.4:2181,", 5000, new GetChildrenSync());
        Thread.sleep(Integer.MAX_VALUE);
    }
    // 同步创建节点
    private void doSomeThing() {
        try {
            List<String> children = zooKeeper.getChildren("/", true);
            System.out.println(children);
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
            System.out.println(watchedEvent.getType());
           if(watchedEvent.getType() == Event.EventType.None && watchedEvent.getPath() == null){
                doSomeThing();
           }else if (watchedEvent.getType() == Event.EventType.NodeChildrenChanged){
               // 监听子节点是否发生变化
               try {
                   System.out.println(zooKeeper.getChildren(watchedEvent.getPath(), true));
               } catch (KeeperException e) {
                   e.printStackTrace();
               } catch (InterruptedException e) {
                   e.printStackTrace();
               }
           }
        }
    }
}
