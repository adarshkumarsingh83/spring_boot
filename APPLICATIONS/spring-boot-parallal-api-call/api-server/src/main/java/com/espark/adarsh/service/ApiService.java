package com.espark.adarsh.service;

import com.espark.adarsh.bean.ApiResponse;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;
import java.util.function.Function;

@Slf4j
@Getter
@Service
public class ApiService {

    private final Function<String, ApiResponse<Map<String,String>>> greetApiFunction = (name) -> {
        log.info("ApiService.apiResponseFunction() : name : {} ", name);
               return  new ApiResponse<Map<String,String>>(
                       Map.of("responseMessage", "Hello " + name + ", Welcome to Espark Application "+ LocalDateTime.now()),"Success");
    };



    private final BiFunction<String,Integer,ApiResponse<Map<String,String>>> greetApiBiFunctionV1 = (name,wait) -> {
        log.info("ApiService.apiResponseFunction() : name : {} ", name);
        try {
            TimeUnit.MINUTES.sleep(wait);
        } catch (InterruptedException e) {
            log.error("ApiService.apiResponseFunction() : error : {} ", e.getMessage());
        }
        return  new ApiResponse<Map<String,String>>(
                Map.of("responseMessage", "Hello " + name + ", Welcome to Espark Application "+ LocalDateTime.now()),"Success");
    };

    private final BiFunction<String,Integer,ApiResponse<Map<String,String>>> greetApiBiFunctionV2 = (name,wait) -> {
        log.info("ApiService.apiResponseFunction() : name : {} ", name);
        try {
            TimeUnit.MINUTES.sleep(wait);
        } catch (InterruptedException e) {
            log.error("ApiService.apiResponseFunction() : error : {} ", e.getMessage());
        }
        return  new ApiResponse<Map<String,String>>(
                Map.of("responseMessage", "Hello " + name + ", Welcome to Espark Application "+ LocalDateTime.now()),"Success");
    };
}
