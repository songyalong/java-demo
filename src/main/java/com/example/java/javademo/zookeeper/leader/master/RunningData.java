package com.example.java.javademo.zookeeper.leader.master;

import java.io.Serializable;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class RunningData implements Serializable {
    public static final long serialVersionUID = 4260577459043203630L;

    //  服务器id

    private long cId;
    // 服务器名称

    private String name;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getcId() {
        return cId;
    }

    public void setcId(long cId) {
        this.cId = cId;
    }
}
