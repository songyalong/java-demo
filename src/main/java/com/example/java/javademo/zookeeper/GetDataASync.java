package com.example.java.javademo.zookeeper;

import org.apache.zookeeper.AsyncCallback;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.apache.zookeeper.data.Stat;

import java.io.IOException;

/**
 * @Author: songyalong
 * @Description: 同步获取节点数据
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class GetDataASync implements Watcher {
    static  ZooKeeper zooKeeper;
    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.137.2:2181,192.168.137.3:2181,192.168.137.4:2181,", 5000, new GetDataASync());
        Thread.sleep(Integer.MAX_VALUE);
    }
    // 同步创建节点
    private void doSomeThing() {
        zooKeeper.getData("/znode_1", true, new IDataCallback(), "获取节点数据");
    }
    @Override
    public void process(WatchedEvent watchedEvent) {
        // 如果是连接状态
        if(watchedEvent.getState() == Event.KeeperState.SyncConnected){
            doSomeThing();
        }
    }

    static class IDataCallback implements AsyncCallback.DataCallback{


        @Override
        public void processResult(int rc, String path, Object context, byte[] bytes, Stat stat) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("resultCode = "+rc).append("\n");
            stringBuffer.append("path = "+path).append("\n");
//            stringBuffer.append("context = "+context).append("\n");
            stringBuffer.append("stat = "+stat).append("\n");
            stringBuffer.append("data = "+new String(bytes)).append("\n");
            System.out.println(stringBuffer.toString());
        }
    }
}
