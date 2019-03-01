package com.example.java.javademo.zookeeper.leader.master;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class LeaderSelectZkClient {
    private static final  int CLIENT_COUNT = 10;
    private static final String SERVER = "localhost:2181";
    public static void main(String[] args) throws Exception {
        List<ZkClient> zkClients = new ArrayList<>();
        List<WorkServer> workServers = new ArrayList<>();
        try{
            for (int i = 0; i < CLIENT_COUNT; i++) {
                ZkClient zkClient = new ZkClient(SERVER, 5000, 5000, new SerializableSerializer());
                RunningData runningData = new RunningData();
                runningData.setcId(Long.valueOf(i));
                runningData.setName("Client"+i);
                WorkServer workServer = new WorkServer(runningData);
                workServer.setZkClient(zkClient);
                workServers.add(workServer);
                workServer.start();
            }
            System.out.println("敲回车键退出！\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        }finally {
            System.out.println("Shutting down....");
            for(WorkServer workServer : workServers){
                workServer.stop();
            }
            for(ZkClient zkClient : zkClients){
                zkClient.close();
            }
        }


    }
}
