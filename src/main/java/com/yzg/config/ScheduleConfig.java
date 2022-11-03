/**
 * Copyright (c) 2021 prosper datamanage programe.
 */

package com.yzg.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.concurrent.Executors;

/**
 * 定时任务配置
 *
 * @author Mark sunlightcs@gmail.com
 */
@Configuration
public class ScheduleConfig implements SchedulingConfigurer {

    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        //这里设置的线程池是corePoolSize也是很关键了，自己根据业务需求设定
        taskRegistrar.setScheduler(Executors.newScheduledThreadPool(20));
    }
}
