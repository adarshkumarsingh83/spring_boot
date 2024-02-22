package com.espark.adarsh.operation.construct.implementation;


import com.espark.adarsh.annotation.ExecutionOperation;
import com.espark.adarsh.operation.construct.ExecutionTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
@ExecutionOperation(name = "defaultExecutionTask")
public class DefaultExecutionTask implements ExecutionTask {

    public Object executeTask(ChunkContext chunkContext) {
        getExecutionContext(chunkContext).put("defaultExecutionTask", "executed ");
        log.info("DefaultExecutionTask task ");
        return null;
    }
}
