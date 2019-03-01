package com.example.java.javademo.quartz;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class SimpleExample {
    public static void main(String[] args) throws SchedulerException {
        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();
        CronScheduleBuilder cronScheduleBuilder = CronScheduleBuilder.cronSchedule("1 * * * * ?");

        JobDetail jobDetail = JobBuilder.newJob()
                .ofType(SimpleJob.class)
                .withIdentity("jobName", "jobGroup")
                .build();

        CronTrigger cronTrigger = TriggerBuilder.newTrigger()
                .forJob("jobName", "jobGroup")
                .withSchedule(cronScheduleBuilder).build();
        scheduler.scheduleJob(jobDetail, cronTrigger);

        // 创建并注册某一个job任务的监听器
        scheduler.getListenerManager()
                .addJobListener(new SimpleJobListener(), KeyMatcher.keyEquals(JobKey.jobKey("jobName", "jobGroup")));



        scheduler.start();

    }
}
