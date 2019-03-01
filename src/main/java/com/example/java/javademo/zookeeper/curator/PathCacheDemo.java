package com.example.java.javademo.zookeeper.curator;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheListener;
import org.apache.curator.retry.ExponentialBackoffRetry;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class PathCacheDemo {
    public static void main(String[] args) throws Exception {
        ExponentialBackoffRetry retry = new ExponentialBackoffRetry(1000, 3);
        CuratorFramework client = CuratorFrameworkFactory.newClient("localhost:2181", 5000, 5000, retry);
        client.start();
        PathChildrenCache pathChildrenCache = new PathChildrenCache(client, "/", true);
        pathChildrenCache.start();

        PathChildrenCacheListener pathChildrenCacheListener = ((curatorFramework, pathChildrenCacheEvent) ->{
            System.out.println("时间类型="+pathChildrenCacheEvent.getType());
            if (null != pathChildrenCacheEvent.getData()){
                ChildData childData = pathChildrenCacheEvent.getData();
                System.out.println("节点数据："+childData.getPath()+" = "+ new String(childData.getData()));
            }

        });








    }
}
