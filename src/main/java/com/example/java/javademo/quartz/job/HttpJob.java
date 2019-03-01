package com.example.java.javademo.quartz.job;

import org.quartz.*;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@DisallowConcurrentExecution
public class HttpJob implements Job {
    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDetail jobDetail = jobExecutionContext.getJobDetail();
        System.out.println(jobDetail.getJobDataMap());
        System.out.println("tttttttttttttt");

    }
}
