package com.espark.adarsh.client.service;

import com.espark.adarsh.client.config.ServerApiConfig;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Supplier;

@Slf4j
@Service
public class ApplicationService {

    ServerApiConfig serverApiConfig;

    RestTemplate restTemplate;

    public ApplicationService(ServerApiConfig serverApiConfig, RestTemplate restTemplate) {
        this.serverApiConfig = serverApiConfig;
        this.restTemplate = restTemplate;
    }


    public Supplier<HttpEntity<String>> httpEntitySupplier=()->{
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(HttpHeaders.ACCEPT,"application/json");
       return  new HttpEntity<>(httpHeaders);
    };


    @CircuitBreaker(name = "processRequest", fallbackMethod = "processRequestCircuitBreakerFallback")
    @Retry(name = "processRequest")
    @RateLimiter(name = "processRequest",fallbackMethod = "processRequestRateLimiterFallback")
    public ResponseEntity<String> processRequest(String request){
        ResponseEntity<String> responseEntity=null;
        try {
            HttpEntity<String> httpEntity = httpEntitySupplier.get();
            responseEntity = restTemplate.exchange(serverApiConfig.getUrl() + serverApiConfig.getApi() + request, HttpMethod.GET,
                    httpEntity, String.class);
        } catch (HttpClientErrorException e) {
            log.error("Exception in client {}  {}",e.getStatusText(),e.getMessage());
            throw e;
        } catch (HttpServerErrorException e){
            log.error("Exception in Server {}  {}",e.getStatusText(),e.getMessage());
            throw e;
        }
        return ResponseEntity.status(200).body(responseEntity.getBody());
    }

    public ResponseEntity<String> processRequestRateLimiterFallback(RequestNotPermitted requestNotPermitted){
        log.info("processRequestRateLimiterFallback {}",requestNotPermitted.getMessage());
        return ResponseEntity.of(Optional.of("processRequestRateLimiterFallback executed "));
    }


    public ResponseEntity<String> processRequestCircuitBreakerFallback(CallNotPermittedException callNotPermittedException){
        log.info("processRequestCircuitBreakerFallback {}",callNotPermittedException.getMessage());
        return ResponseEntity.of(Optional.of("processRequestCircuitBreakerFallback executed "));
    }

}
