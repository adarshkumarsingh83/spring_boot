package com.espark.adarsh.operation;


import com.espark.adarsh.annotation.PreExecutionOperation;
import com.espark.adarsh.operation.construct.PreExecutionTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
@PreExecutionOperation(name = "samplePreExecutionTask")
public class SamplePreExecutionTask implements PreExecutionTask {

    @Override
    public Object executePreTask(ChunkContext chunkContext) {
        getExecutionContext(chunkContext).put("samplePreExecutionTask", "executed ");
        log.info("SamplePreExecutionTask  task completed");
        return null;
    }
}
