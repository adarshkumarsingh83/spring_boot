package com.espark.adarsh.task;

import com.espark.adarsh.config.BatchConfiguration;
import com.espark.adarsh.operation.construct.PostExecutionTask;
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
public class OperationPostExecutionTask implements Tasklet {


    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        log.info("OperationPostExecutionTask start..");
        LinkedHashMap<String, PostExecutionTask> postExecutionOperationsMap =
                BatchConfiguration.getPostExecutionOperations();
        if (postExecutionOperationsMap != null) {
            for (Map.Entry<String, PostExecutionTask> entry : postExecutionOperationsMap.entrySet()) {
                String taskName = entry.getKey();
                PostExecutionTask postExecutionTask = entry.getValue();
                log.info("execution is starting for task={}", taskName);
                postExecutionTask.executePostTask(chunkContext);
                log.info("execution is completed for task={}", taskName);
            }
        } else {
            log.info("OperationPostExecutionTask not found ");
        }

        log.info("OperationPostExecutionTask done..");
        return RepeatStatus.FINISHED;
    }
}