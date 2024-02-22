package com.espark.adarsh.confi;

import com.espark.adarsh.listner.CommonBatchJobListener;
import com.espark.adarsh.task.DataTasklet;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Slf4j
@Configuration
@EnableBatchProcessing
public class DataDisplayTaskletConfig {


    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Autowired
    DataTasklet dataTasklet;

    @Bean
    public Step initDataDisplayStep() {
        return steps.get("initDataDisplayStep")
                .tasklet(dataTasklet)
                .build();
    }

    @Bean(name = "dataDisplayJob")
    public Job dataDisplayJob() {
        return jobs.get("dataDisplayJob")
                .start(initDataDisplayStep())
                .listener(new CommonBatchJobListener())
                .preventRestart()
                .build();
    }

}
