package com.espark.adarsh.web;

import com.espark.adarsh.bean.ApiResponse;
import com.espark.adarsh.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Slf4j
@RestController
public class ApiController {

    private ApiService apiService;

    public ApiController(ApiService apiService) {
        this.apiService = apiService;
    }

    @GetMapping("/api/wish/{name}")
    public ApiResponse<Map<String,String>> getApiFunction(@PathVariable("name") String name) {
        log.info("ApiController.getApiResponse() : name : {} ", name);
        return this.apiService.getGreetApiFunction().apply(name);
    }

    @GetMapping("/v1/api/wish/{name}/{wait}")
    public ApiResponse<Map<String,String>> getApiBiFunctionV1(@PathVariable("name") String name,
                                                            @PathVariable("wait") Integer wait) {
        log.info("ApiController.getApiResponse() : name : {} wait : {} ", name, wait);
        return this.apiService.getGreetApiBiFunctionV1().apply(name,wait);
    }

    @GetMapping("/v2/api/wish/{name}/{wait}")
    public ApiResponse<Map<String,String>> getApiBiFunctionV2(@PathVariable("name") String name,
                                                            @PathVariable("wait") Integer wait) {
        log.info("ApiController.getApiResponse() : name : {} wait : {} ", name, wait);
        return this.apiService.getGreetApiBiFunctionV2().apply(name,wait);
    }
}
