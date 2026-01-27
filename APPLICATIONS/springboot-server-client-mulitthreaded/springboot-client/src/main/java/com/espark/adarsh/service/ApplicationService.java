package com.espark.adarsh.service;

import com.espark.adarsh.bean.User;
import com.espark.adarsh.config.ApplicationProps;
import com.espark.adarsh.factory.PoolObjectFactory;
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
@Service
public class ApplicationService {

    private ApplicationProps applicationProps;
    private PoolObjectFactory <User> userPoolObjectFactory;
    private RestTemplate restTemplate ;

    public ApplicationService(ApplicationProps applicationProps,
                              PoolObjectFactory<User> userPoolObjectFactory,
                              RestTemplate restTemplate) {
        this.applicationProps = applicationProps;
        this.userPoolObjectFactory = userPoolObjectFactory;
        this.restTemplate = restTemplate;
    }

    @Async
    public void sendRequest(int number) throws InterruptedException {
        List<Future<String>> futureList = new
                LinkedList<>();
        ExecutorService executor = Executors.newFixedThreadPool(5);
        List<String> data =  IntStream.range(1,number)
               .mapToObj(i -> "adarsh"+i)
               .collect(Collectors.toList());

        ConcurrentLinkedQueue<String> dataQueue = new ConcurrentLinkedQueue<>(data);

         String dataItem=null;
        do{
            dataItem = dataQueue.poll();
            if(dataItem!=null) {
                String finalDataItem = dataItem;
                User user = userPoolObjectFactory.getObject();
                user.setName(finalDataItem);
                user.setEmail(finalDataItem+"@espark.com");
                Future<String> future = executor.submit(() -> wishUser(user));
                futureList.add(future);
            }
        } while(dataItem!=null);

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
