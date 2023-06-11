package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ThreadLocalRandom;


@Slf4j
@Service
public class ApplicationService {


    @Async
    public CompletableFuture<String> wish(){
          randomDelay();
        return CompletableFuture.completedFuture(System.getProperty("user.name")+" Welcome to the Espark ");
    }

    private void randomDelay(){
        try{
            Thread.sleep(ThreadLocalRandom.current().nextInt(5000));
        }catch (InterruptedException e){
            log.error("exception {}",e.getMessage());
        }
    }

}
