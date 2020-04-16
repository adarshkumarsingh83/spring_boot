package com.espark.adarsh.task;

import com.espark.adarsh.service.DataDisplayService;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DataTasklet implements Tasklet {

    @Autowired
    DataDisplayService dataDisplayService;

    @Override
    public RepeatStatus execute(StepContribution contribution,
                                ChunkContext chunkContext)
            throws Exception {
        this.dataDisplayService.showData();
        return RepeatStatus.FINISHED;
    }

}
