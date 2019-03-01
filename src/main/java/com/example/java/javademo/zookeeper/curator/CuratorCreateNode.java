package com.example.java.javademo.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.data.Stat;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class CuratorCreateNode {
    public static void main(String[] args) throws Exception {
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.builder()
                .connectString("localhost:2181")
                .sessionTimeoutMs(5000)
                .connectionTimeoutMs(5000)
                .retryPolicy(retry)
                .build();
        client.start();
        client.delete().forPath("/znode_1");
        client.delete().deletingChildrenIfNeeded().forPath("/znode_1");
        client.delete().deletingChildrenIfNeeded().withVersion(10086).forPath("/znode_1");
        client.delete().guaranteed().forPath("/znode_1");


        client.getData().forPath("/znode_1");
        Stat stat = new Stat();
        client.getData().storingStatIn(stat).forPath("/znode_1");

        client.setData().forPath("/znode_1");
        client.setData().withVersion(10086).forPath("/znode_1");

        client.checkExists().forPath("/znode_1");

        client.getChildren().forPath("/znode_1");

        client.inTransaction()
                .create().forPath("").
                and()
                .setData().forPath("/", "123".getBytes())
                .and()
                .commit();


    }

}
