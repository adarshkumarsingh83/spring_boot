package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@Service
public class ApplicationService {

    private final TaskExecutor taskExecutor;

    public ApplicationService(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public CompletableFuture<String> wish(){
        return CompletableFuture.supplyAsync(()->{
          randomDelay();
          return System.getProperty("user.name")+" Welcome to the Espark ";
        },taskExecutor);
    }

    private void randomDelay(){
        try{
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
        }catch (InterruptedException e){
            log.error("exception {}",e.getMessage());
        }
    }

}
