package com.espark.adarsh.service;

import com.espark.adarsh.bean.User;
import com.espark.adarsh.config.ApplicationProps;
import com.espark.adarsh.evernt.JobEventPublisher;
import com.espark.adarsh.evernt.JobState;
import com.espark.adarsh.factory.PoolObjectFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Data
@Service
public class ApplicationService {

    private ApplicationProps applicationProps;
    private PoolObjectFactory <User> userPoolObjectFactory;
    private RestTemplate restTemplate ;

    ConcurrentLinkedQueue<List<User>> dataQueue = new ConcurrentLinkedQueue<>();
    JobState jobState = JobState.NOJOB;

    public ApplicationService(ApplicationProps applicationProps,
                              PoolObjectFactory<User> userPoolObjectFactory,
                              RestTemplate restTemplate) {
        this.applicationProps = applicationProps;
        this.userPoolObjectFactory = userPoolObjectFactory;
        this.restTemplate = restTemplate;
    }

    public void setData(List<User> users){
        dataQueue.add(users);
    }

    public void sendRequest(){
        List<Future<String>> futureList = new
                LinkedList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        int batch =1;
        do {
            List<User> userList = dataQueue.poll();
            if (userList != null) {
                log.info("processing batch {}",batch);
                userList.parallelStream()
                        .forEach(user -> {
                            Future<String> future = executor.submit(() -> wishUser(user));
                            futureList.add(future);
                        });
                batch++;
            }

        } while (!this.jobState.name().equals(JobState.STOPJOB.name())
                && !this.jobState.name().equals(JobState.ABORTJOB.name()));

        futureList.stream().forEach(f -> {
            try {
                String result = f.get();
                // log.info("Result from future: {}", result);
            } catch (Exception e) {
                log.error("Error getting future result", e);
            }
        });
        executor.shutdown();

    }

    public String wishUser(User user) {
        log.info("Sending request thread: {} for user: {}", Thread.currentThread().getName(), user);
        String response =  restTemplate.postForObject(applicationProps.getApiUrl(), user, String.class);
        log.info("Received response thread: {} for user: {} : {}", Thread.currentThread().getName(), user, response);
        userPoolObjectFactory.returnObject(user);
        return response;
    }


}
