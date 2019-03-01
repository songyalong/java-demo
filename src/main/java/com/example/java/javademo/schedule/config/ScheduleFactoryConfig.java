package com.example.java.javademo.schedule.config;

import org.springframework.context.annotation.Configuration;


/**
 * @Author: songyalong
 * @Description:
 * @Date: Created in ${time}${date}
 * @Modified By:
 */
@Configuration
public class ScheduleFactoryConfig {

    private boolean schedulerAutoStartup = true;

//    @Bean
//    public SchedulerFactoryBean scheduler(DataSource dataSource){
//        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
//        // 设置数据源，设置数据源，使用与项目统一数据源
//        factoryBean.setDataSource(dataSource);
//        // 设置调度器自动运行
//        factoryBean.setAutoStartup(schedulerAutoStartup);
//        // 设置配置文件
//        //schedulerFactoryBean.setConfigLocation(new ClassPathResource("/quartz.properties"));
//        //项目启动完成后，等待2秒后开始执行调度器初始化
//        //schedulerFactoryBean.setStartupDelay(2);
//        return factoryBean;
//    }

//    @Bean
//    public DynamicJobManager dynamicSchedulerFactory(Scheduler scheduler) {
//        DynamicJobManager schedulerFactory = new DynamicJobManager();
//        schedulerFactory.setScheduler(scheduler);
//        return schedulerFactory;
//    }

}
