package com.example.java.javademo.zookeeper;

import org.apache.zookeeper.*;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;
import java.util.List;

/**
 * @Author: songyalong
 * @Description: 异步获取子节点
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class GetChildrenASync implements Watcher {
    static  ZooKeeper zooKeeper;
    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.137.2:2181,192.168.137.3:2181,192.168.137.4:2181,", 5000, new GetChildrenASync());
        Thread.sleep(Integer.MAX_VALUE);
    }
    // 同步创建节点
    private void doSomeThing() {
           zooKeeper.getChildren("/", true, new IChildrenCallBack(), "异步获取子节点");
    }
    @Override
    public void process(WatchedEvent watchedEvent) {

        // 如果是连接状态
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
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

    static class IChildrenCallBack implements AsyncCallback.Children2Callback{

        @Override
        public void processResult(int rc, String path, Object context, List<String> list, Stat stat) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("resultCode = "+rc).append("\n");
            stringBuffer.append("path = "+path).append("\n");
            stringBuffer.append("stat = "+stat).append("\n");
            System.out.println(stringBuffer.toString());
        }
    }
}
