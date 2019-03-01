package com.example.java.javademo.quartz;

import com.example.java.javademo.quartz.job.HttpJob;
import org.quartz.CronTrigger;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.TriggerKey;

/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
public class DynamicJobManager {

    private static Scheduler scheduler;

    public static void setScheduler(Scheduler scheduler) {
        DynamicJobManager.scheduler = scheduler;
    }

    public boolean enable(){

        return false;
    }

    private boolean startupMonitoringJob() throws SchedulerException {

        DynamicJob dynamicJob = new DynamicJob().cronExpression("0/30 * * * * ?")
                .jobGroup("jobGroup")
                .jobName("test")
                .addJobData("test1", "test2")
                .target(HttpJob.class);

        CronTrigger cronTrigger = dynamicJob.cronTrigger();
        TriggerKey triggerKey = dynamicJob.triggerKey();
        scheduler.rescheduleJob(triggerKey, cronTrigger);
        return false;
    }


    private boolean pauseJob(){

        return false;
    }

    private boolean delete(){

        return false;
    }












}
