package com.example.java.javademo.zookeeper.zkclient;

import org.I0Itec.zkclient.ZkClient;
import org.I0Itec.zkclient.serialize.SerializableSerializer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class TestWorkClient {
    public static final int CLINET_COUNT = 10;
    public static final String SERVER = "localhost:2181";
    public static void main(String[] args) {
        List<ZkClient> zkClients = new ArrayList<>();
        List<WorkClient> workClients = new ArrayList<>();
        try {
            for (int i = 0; i < CLINET_COUNT; i++) {
                ZkClient zkClient = new ZkClient(SERVER, 5000, 5000, new SerializableSerializer());
                zkClients.add(zkClient);
                WorkClient workClient = new WorkClient(zkClient, "Client"+i);
                workClients.add(workClient);
                workClient.start();
            }
            System.out.println("敲回车键退出！\n");
            new BufferedReader(new InputStreamReader(System.in)).readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            System.out.println("shut down");
            for(WorkClient workClient : workClients){
                workClient.stop();
            }
            for(ZkClient zkClient : zkClients){
                zkClient.close();
            }
        }

    }
}
