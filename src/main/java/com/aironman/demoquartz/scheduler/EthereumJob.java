package com.aironman.demoquartz.scheduler;

import com.aironman.demoquartz.service.EthereumJobService;
import org.quartz.DisallowConcurrentExecution;
import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@DisallowConcurrentExecution
public class EthereumJob implements Job{

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private EthereumJobService jobService;

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {

        logger.info("EthereumJob ** {} ** fired @ {}", context.getJobDetail().getKey().getName(), context.getFireTime());

        jobService.executeJob();

        logger.info("Next EthereumJob scheduled @ {}", context.getNextFireTime());

    }
}
