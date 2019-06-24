package com.espark.adarsh.operation.construct;


import org.springframework.batch.core.scope.context.ChunkContext;

public interface PreExecutionTask extends Task {

    Object executePreTask(ChunkContext chunkContext);


}
