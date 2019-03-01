package com.example.java.javademo.zookeeper.zkclient;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class WorkClient {
    private ZkClient zkClient;
    private String clientName;
    private String MASTER = "/master";
    private boolean isRunning;
    private IZkDataListener iZkDataListener;
    private ScheduledExecutorService executorService = new ScheduledThreadPoolExecutor(1);

    public WorkClient(ZkClient zkClient, String clientName){
        this.zkClient = zkClient;
        this.clientName = clientName;
        this.iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                // 当节点删除时，3秒后重新选举
                executorService.schedule(new Runnable() {
                    @Override
                    public void run() {
                        taskMaster();
                    }
                }, 3, TimeUnit.SECONDS);
            }
        };
    }

    private void taskMaster() {
        // 创建master节点
        System.out.println(clientName+" server  开始竞争master");
        try{
            zkClient.create(MASTER, clientName, CreateMode.EPHEMERAL);
            System.out.println(clientName+ " 成功选举为master");
            executorService.schedule(new Runnable() {
                @Override
                public void run() {
                    releaseMaster();
                }
            }, 3, TimeUnit.SECONDS);
        }catch (ZkNodeExistsException e){
            return;
        }
    }

    public void releaseMaster(){
        zkClient.delete(MASTER);
    }

    public void start(){

        if(isRunning){
            return;
        }
        isRunning = true;
        System.out.println(clientName+" server start");
        taskMaster();
        zkClient.subscribeDataChanges(MASTER, iZkDataListener);
    }

    public void stop(){
        if(!isRunning){
            return;
        }

        System.out.println(clientName + " server stop");
        zkClient.unsubscribeDataChanges(MASTER, iZkDataListener);
        executorService.shutdown();


    }



}
