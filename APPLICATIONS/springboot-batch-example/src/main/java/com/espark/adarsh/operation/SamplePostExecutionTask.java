package com.espark.adarsh.operation;


import com.espark.adarsh.annotation.PostExecutionOperation;
import com.espark.adarsh.operation.construct.PostExecutionTask;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.scope.context.ChunkContext;

@Slf4j
@PostExecutionOperation(name = "samplePostExecutionTask")
public class SamplePostExecutionTask implements PostExecutionTask {

    @Override
    public Object executePostTask(ChunkContext chunkContext) {
        getExecutionContext(chunkContext).put("samplePostExecutionTask", "executed ");
        log.info("SamplePostExecutionTask task completed");
        return null;
    }
}
