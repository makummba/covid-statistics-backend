package com.kasprusz.covidstatisticsbackend.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.stereotype.Component;

@Component
public class HistoricalJobNotificationListener extends JobExecutionListenerSupport {

    private static final Logger log = LoggerFactory.getLogger(HistoricalJobNotificationListener.class);


    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
            log.info("============ HISTORICAL DATA IMPORTED ============ ....\n");
        }
    }

}