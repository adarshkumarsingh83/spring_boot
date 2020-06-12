package com.espark.adarsh.operation.construct;


import org.springframework.batch.core.scope.context.ChunkContext;

public interface ExecutionTask extends Task {

    Object executeTask(ChunkContext chunkContext);
}
