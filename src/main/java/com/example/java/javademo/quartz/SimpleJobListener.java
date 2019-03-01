package com.example.java.javademo.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class SimpleJobListener implements JobListener {
    @Override
    public String getName() {
        return SimpleJobListener.class.getName();
    }

    @Override
    public void jobToBeExecuted(JobExecutionContext jobExecutionContext) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println(name+" is going to be executed....");
    }

    @Override
    public void jobExecutionVetoed(JobExecutionContext jobExecutionContext) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println(name+" job execution vetoed....");
    }

    @Override
    public void jobWasExecuted(JobExecutionContext jobExecutionContext, JobExecutionException e) {
        String name = jobExecutionContext.getJobDetail().getKey().getName();
        System.out.println(name + " was executed....");
    }
}
