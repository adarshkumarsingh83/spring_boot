package com.espark.adarsh.operation.construct.implementation;


import com.espark.adarsh.annotation.PreExecutionOperation;
import com.espark.adarsh.operation.construct.PreExecutionTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
@PreExecutionOperation(name = "defaultPreExecutionTask")
public class DefaultPreExecutionTask implements PreExecutionTask {

    @Override
    public Object executePreTask(ChunkContext chunkContext) {
        getExecutionContext(chunkContext).put("defaultPreExecutionTask", "executed ");
        log.info("DefaultPreExecutionTask task ");
        return null;
    }
}
