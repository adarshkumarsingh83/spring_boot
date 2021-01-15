package com.espark.adarsh.task;

import com.espark.adarsh.config.BatchConfiguration;
import com.espark.adarsh.operation.construct.PreExecutionTask;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

import java.util.LinkedHashMap;
import java.util.Map;

@Slf4j
@Data
@Component
public class OperationPreExecutionTask implements Tasklet {


    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("OperationPreExecutionTask start..");
        LinkedHashMap<String, PreExecutionTask> preExecutionOperationsMap =
                BatchConfiguration.getPreExecutionOperations();
        if (preExecutionOperationsMap != null) {
            for (Map.Entry<String, PreExecutionTask> entry : preExecutionOperationsMap.entrySet()) {
                String taskName = entry.getKey();
                PreExecutionTask preExecutionTask = entry.getValue();
                log.info("execution is starting for task={}", taskName);
                preExecutionTask.executePreTask(chunkContext);
                log.info("execution is completed for task={}", taskName);
            }
        } else {
            log.info("OperationPreExecutionTask not found ");
        }

        log.info("OperationPreExecutionTask done..");
        return RepeatStatus.FINISHED;
    }
}