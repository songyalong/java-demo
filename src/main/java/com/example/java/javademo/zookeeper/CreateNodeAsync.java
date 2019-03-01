package com.example.java.javademo.zookeeper;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * @Author: songyalong
 * @Description: 同步创建节点
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class CreateNodeAsync implements Watcher {
    static  ZooKeeper zooKeeper;
    public static void main(String[] args) throws IOException, InterruptedException {
        zooKeeper = new ZooKeeper("192.168.137.2:2181,192.168.137.3:2181,192.168.137.4:2181,", 5000, new CreateNodeAsync());
        Thread.sleep(Integer.MAX_VALUE);
    }
    // 异步创建节点
    private void doSomeThing() throws KeeperException, InterruptedException {
       zooKeeper.create("/znode_2", "123".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT, new IStringCallBack(), "创建");
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

    static class IStringCallBack implements AsyncCallback.StringCallback{

        @Override
        public void processResult(int i, String s, Object o, String s1) {
            StringBuffer stringBuffer = new StringBuffer();
            stringBuffer.append("resutCode = "+ i).append("\n");
            stringBuffer.append("s = "+ s).append("\n");
            stringBuffer.append("name = "+ s1).append("\n");
            System.out.println(stringBuffer.toString());
        }
    }

}
