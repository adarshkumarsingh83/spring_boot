package com.espark.adarsh.operation.construct.implementation;


import com.espark.adarsh.annotation.PostExecutionOperation;
import com.espark.adarsh.operation.construct.PostExecutionTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
@PostExecutionOperation(name = "defaultPostExecutionTask")
public class DefaultPostExecutionTask implements PostExecutionTask {

    @Override
    public Object executePostTask(ChunkContext chunkContext) {
        getExecutionContext(chunkContext).put("defaultPostExecutionTask", "executed ");
        log.info("DefaultPostExecutionTask task ");
        return null;
    }
}
