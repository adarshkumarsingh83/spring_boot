package com.espark.adarsh.operation.construct;


import org.springframework.batch.core.scope.context.ChunkContext;

public interface PostExecutionTask extends Task {

    Object executePostTask(ChunkContext chunkContext);
}
