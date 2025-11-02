package com.espark.adarsh.web;

import com.espark.adarsh.bean.ApiResponse;
import com.espark.adarsh.service.ApiExecutorService;
import com.espark.adarsh.service.ApiService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.function.Function;
import java.util.stream.IntStream;

@Slf4j
@RestController
public class ApiController {

    private ApiService apiService;

    private ApiExecutorService apiExecutorService;

    public ApiController(ApiService apiService,
                         ApiExecutorService apiExecutorService) {
        this.apiService = apiService;
        this.apiExecutorService = apiExecutorService;
    }

    @GetMapping("api/greet/{name}/{count}")
    public String greetUser(@PathVariable("name") String name, @PathVariable("count") int count) {
        apiService.callApiFromTeak1(name+"1", count);
        apiService.callApiFromTeak2(name+"2", count);
        return "Api Calls are in Process Check Logs for Response";
    }

    @GetMapping("v1/api/greet/{name}/{count}")
    public ApiResponse<List<Map<String,String>>> greetUsers(@PathVariable("name") String name,
                                                            @PathVariable("count") int count) {
        log.info("ApiController::greetUsers name {} count {}",name,count);
        ApiResponse<List<Map<String,String>>> response  =  this.apiExecutorService.executeApiCalls(name, count, generateWaitCounts.apply(count));
        response.getData().stream()
                .forEach(e -> log.info(e.toString()));
        return response;
    }

    private Function<Integer, List<Integer>> generateWaitCounts = ( count) -> {
       return IntStream.range(0,count)
               .map(i->new Random().nextInt(5)+1)
               .boxed()
               .toList();
    };
}
