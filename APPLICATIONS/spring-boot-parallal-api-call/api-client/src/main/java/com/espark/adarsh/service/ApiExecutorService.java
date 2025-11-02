package com.espark.adarsh.service;

import com.espark.adarsh.bean.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Service
public class ApiExecutorService {

    private RestTemplate restTemplate;

    public ApiExecutorService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Async("apiTaskExecutor")
    private CompletableFuture<Map<String, String>> callApiFromTask(String url, String name, int waitCount) {
        log.info("ApiService.callApiFromTask() : name : {} , waitCount : {} ", name, waitCount);
        Map<String, String> response = this.restTemplate.getForObject(url, Map.class, name, waitCount);
        log.info("ApiService.callApiFromTask() : response for waitCount {} : {} ", waitCount, response);
        return CompletableFuture.completedFuture(response);
    }

    public ApiResponse<List<Map<String, String>>> executeApiCalls(String name, int count, List<Integer> waitCount) {
        log.info("ApiService.executeApiCalls() : name : {} , count : {} , waitCount : {} ", name, count, waitCount);
        List<CompletableFuture<Map<String, String>>> futureList = new LinkedList<>();
        for (int i = 0; i < count; i++) {
            log.info("ApiService.executeApiCalls() : processing count : {} ", i);
            if(i%2==0) {
                futureList.add(callApiFromTask("http://localhost:8090/v1/api/wish/{name}/{waitCount}",name + "_" + i, waitCount.get(i)));
            }else{
                futureList.add(callApiFromTask("http://localhost:8090/v2/api/wish/{name}/{waitCount}",name + "_" + i, waitCount.get(i)));
            }
        }
        CompletableFuture.allOf(futureList.toArray(new CompletableFuture[0])).join();
        List<Map<String, String>> responses = futureList.stream().map(CompletableFuture::join).toList();
        log.info("ApiService.executeApiCalls() : responses : {} ", responses);
        return new ApiResponse<List<Map<String, String>>>(responses, "Success");
    }

}
