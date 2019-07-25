package com.aironman.demoquartz.scheduler;

import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.aironman.demoquartz.service.SampleJobService;

// component to fire bitcoin quartz job (SampleJobService)
@Component
@DisallowConcurrentExecution
public class SampleJob implements Job {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SampleJobService jobService;

    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("SampleJob ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());

        jobService.executeSampleJob();

        logger.info("Next SampleJob scheduled @ {}", context.getNextFireTime());
    }
}
