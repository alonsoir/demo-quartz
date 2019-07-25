/*
package com.aironman.demoquartz.scheduler;

import org.quartz.JobDetail;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SimpleTriggerFactoryBean;

@Configuration
public class QuartzSubmitJobs {

    private static final String CRON_EVERY_FIVE_MINUTES = "0 0/5 * ? * * *";
    @Bean(name = "ethereumStats")
    public JobDetailFactoryBean jobMemberStats() {
        return QuartzConfig.createJobDetail(EthereumJob.class, "ethereumStats Job");
    }
    @Bean(name = "ethereumStatsTrigger")
    public SimpleTriggerFactoryBean triggerMemberStats(@Qualifier("ethereumStats") JobDetail jobDetail) {
        return QuartzConfig.createTrigger(jobDetail, 60000, "ethereumStats Trigger");
    }
    @Bean(name = "sampleStats")
    public JobDetailFactoryBean jobMemberClassStats() {
        return QuartzConfig.createJobDetail(SampleJob.class, "Sample BTC Job");
    }
    @Bean(name = "memberClassStatsTrigger")
    public CronTriggerFactoryBean triggerMemberClassStats(@Qualifier("sampleStats") JobDetail jobDetail) {
        return QuartzConfig.createCronTrigger(jobDetail, CRON_EVERY_FIVE_MINUTES, "Class Statistics Trigger");
    }

}
*/