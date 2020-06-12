package com.espark.adarsh.listener;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.batch.item.ExecutionContext;
import org.springframework.stereotype.Component;

import java.util.Map;

@Slf4j
@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

    @Override
    public void afterJob(JobExecution jobExecution) {
        ExecutionContext context = jobExecution.getExecutionContext();
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            for (Map.Entry<String, Object> entry : context.entrySet()) {
                log.info("==> "+entry.getKey() + " " + entry.getValue());
            }
        }
    }

}
