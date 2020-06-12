package com.espark.adarsh.task;

import com.espark.adarsh.config.BatchConfiguration;
import com.espark.adarsh.operation.construct.ExecutionTask;
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
public class OperationExecutionTask implements Tasklet {

    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("OperationExecutionTask start..");
        LinkedHashMap<String, ExecutionTask> executionOperationsMap = BatchConfiguration.getExecutionOperations();
        if (executionOperationsMap != null) {
            for (Map.Entry<String, ExecutionTask> entry : executionOperationsMap.entrySet()) {
                String taskName = entry.getKey();
                ExecutionTask executionTask = entry.getValue();
                log.info("execution is starting for task={}", taskName);
                executionTask.executeTask(chunkContext);
                log.info("execution is completed for task={}", taskName);
            }
        } else {
            log.info("OperationExecutionTask not found ");
        }

        log.info("OperationExecutionTask done..");
        return RepeatStatus.FINISHED;
    }
}