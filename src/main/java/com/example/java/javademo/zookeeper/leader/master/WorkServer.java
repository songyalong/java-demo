package com.example.java.javademo.zookeeper.leader.master;

import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.exception.ZkException;
import org.I0Itec.zkclient.exception.ZkInterruptedException;
import org.I0Itec.zkclient.exception.ZkNoNodeException;
import org.I0Itec.zkclient.exception.ZkNodeExistsException;
import org.apache.zookeeper.CreateMode;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class WorkServer {
    private boolean isRunning = false;
    private RunningData serverData;
    private RunningData masterData;
    private static final String MASTER = "/master";
    private IZkDataListener iZkDataListener;
    private ZkClient zkClient;

    // 防止网络抖动，造成竞争master不必要的开销， 使用调度器
    private ScheduledExecutorService scheduledExecutorService = new ScheduledThreadPoolExecutor(1);
    private int delayTime = 5;


    public  WorkServer(RunningData serverData){
        this.serverData = serverData;
        // 增加监听器，当master删除时，竞争创建master节点
        iZkDataListener = new IZkDataListener() {
            @Override
            public void handleDataChange(String s, Object o) throws Exception {

            }

            @Override
            public void handleDataDeleted(String s) throws Exception {
                // 判断是不是主节点, 不是主节点，延迟5m竞争
                taskMaster();
//                if(null != masterData.getName() && masterData.getName().equals(serverData.getName())){
//                    taskMaster();
//                }else {
//                    scheduledExecutorService.schedule(new Runnable() {
//                        @Override
//                        public void run() {
//                            taskMaster();
//                        }
//                    }, delayTime, TimeUnit.SECONDS);
//                }

            }
        };

    }

    public void start() throws Exception {
        // 判断master是否已经存在
        if(isRunning){
            throw new Exception("server has start...");
        }
        isRunning = true;
        zkClient.subscribeDataChanges(MASTER, iZkDataListener);
        taskMaster();

    }

    public void taskMaster() {
        if(!isRunning){
            return;
        }
        // 创建master节点
        try{
            zkClient.create(MASTER, serverData, CreateMode.EPHEMERAL);
            masterData = serverData;
        }catch (ZkNodeExistsException e){
            // 如果master节点已经存在
            RunningData runningData = zkClient.readData(MASTER);
            if(null == runningData){
                // 正好宕机
                taskMaster();
            }else{
                masterData = runningData;
            }
        }
    }

    public void stop() throws Exception{
        // 停止服务
        if (!isRunning){
            throw new Exception("server has stoped....");
        }

        isRunning = false;
        // 取消master上的监听器
        zkClient.unsubscribeDataChanges(MASTER, iZkDataListener);
        // 释放maste的权利
        releaseMaster();
    }

    public void releaseMaster(){
        if(checkMaster()){
            zkClient.delete(MASTER);
        }
    }

    public boolean checkMaster() {
        try{
            RunningData runningData = zkClient.readData(MASTER);
            if(runningData.getName().equals(masterData.getName())){
                return true;
            }
        }catch (ZkNoNodeException e){
            return false;
        }catch (ZkInterruptedException e){
           return checkMaster();
        }catch(ZkException e){
            return false;
        }
        return false;
    }


    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }
}
