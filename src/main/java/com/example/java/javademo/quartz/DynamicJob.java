package com.example.java.javademo.quartz;

import org.quartz.*;

import java.util.Map;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class DynamicJob {

    private String cronExpression;
    private String jobName;

    // 默认使用Scheduler的默认

    private String jobGroup = Scheduler.DEFAULT_GROUP;

    // 要执行的类

    private Class<? extends Job> target;

    // 执行的job任务

    private transient JobDetail jobDetail;

    //
    private transient TriggerKey triggerKey;

    public DynamicJob target(Class<? extends Job> target){
        this.target = target;
        return this;
    }

    public DynamicJob cronExpression(String cronExpression){
        this.cronExpression = cronExpression;
        return this;
    }

    public DynamicJob jobGroup(String jobGroup){
        this.jobGroup = jobGroup;
        return this;
    }

    public DynamicJob jobName(String jobName) {
        this.jobName = jobName;
        return this;
    }

    public CronTrigger cronTrigger(){
        ScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);
        return (CronTrigger) TriggerBuilder.newTrigger().withIdentity(triggerKey())
                .withSchedule(scheduleBuilder)
                .build();
    }

    public TriggerKey triggerKey() {
        if(null == this.triggerKey){
            triggerKey = TriggerKey.triggerKey(this.jobName, this.jobGroup);
        }
        return this.triggerKey;
    }

    // 创建任务
    public JobDetail jobDetail(){
        if(null == jobDetail){
            jobDetail = JobBuilder.newJob(target).withIdentity(jobName, jobGroup).build();
        }
        return jobDetail;
    }

    public DynamicJob addJobData(String key, Object value){
        final JobDetail jobDetail = jobDetail();
        final JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.put(key, value);
        return this;
    }

    public DynamicJob addJobData(Map<String, Object> data){
        final JobDetail jobDetail = jobDetail();
        final JobDataMap jobDataMap = jobDetail.getJobDataMap();
        jobDataMap.putAll(data);
        return this;
    }


























}
