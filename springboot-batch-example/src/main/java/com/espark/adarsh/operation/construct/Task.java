package com.espark.adarsh.operation.construct;

import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.item.ExecutionContext;

public interface Task {

    default ExecutionContext getExecutionContext(ChunkContext chunkContext) {
        return chunkContext.getStepContext()
                .getStepExecution()
                .getJobExecution()
                .getExecutionContext();
    }

}
