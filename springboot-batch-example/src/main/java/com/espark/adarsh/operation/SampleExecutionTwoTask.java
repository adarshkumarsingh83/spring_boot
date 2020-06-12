package com.espark.adarsh.operation;


import com.espark.adarsh.annotation.ExecutionOperation;
import com.espark.adarsh.operation.construct.ExecutionTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
@ExecutionOperation(name = "sampleExecutionTwoTask")
public class SampleExecutionTwoTask implements ExecutionTask {

    public Object executeTask(ChunkContext chunkContext) {
        getExecutionContext(chunkContext).put("sampleExecutionTwoTask", "executed ");
        log.info("SampleExecutionTwoTask task completed");
        return null;
    }
}


