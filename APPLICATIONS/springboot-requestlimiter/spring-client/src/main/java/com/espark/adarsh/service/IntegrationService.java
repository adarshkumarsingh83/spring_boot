package com.espark.adarsh.service;

import com.espark.adarsh.bean.RequestBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.record.Employee;
import com.espark.adarsh.util.Constants;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.function.BiFunction;
import java.util.function.Function;


@Service
public class IntegrationService {

    private static Logger log = LoggerFactory.getLogger(IntegrationService.class);

    static final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${api.server.url}")
    private String apiServerUrl;

    private RestTemplate restTemplate;

    public IntegrationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Function<RequestBean<Employee>, String> getJson = (employee) -> {
        try {
            return objectMapper.writeValueAsString(employee);
        } catch (Exception e) {
            log.error("Exception Generated while parsing employee to json {}", e.getMessage());
            return null;
        }
    };

    @RateLimiter(name = Constants.POST_API_RATE_LIMITER, fallbackMethod = "exceededRateLimitFallbackPostEmployee")
    @CircuitBreaker(name = Constants.POST_API_CIRCUIT_BREAKER, fallbackMethod = "circuitBreakerFallbackPostEmployee")
    @Retry(name = Constants.POST_API_RETRY)
    public ResponseBean<Employee> postEmployee(RequestBean<Employee> requestBean) {
        try {
            HttpHeaders httpHeaders = new HttpHeaders();
            httpHeaders.set("Content-Type", "application/json");
            httpHeaders.set("id", requestBean.getData().id());
            HttpEntity<String> httpEntity = new HttpEntity<>(getJson.apply(requestBean), httpHeaders);
            ResponseEntity<ResponseBean<Employee>> responseEntity = restTemplate.exchange(apiServerUrl,
                    HttpMethod.POST, httpEntity, new ParameterizedTypeReference<ResponseBean<Employee>>() {
                    });
            if (responseEntity.getStatusCode().is2xxSuccessful()) {
                return responseEntity.getBody();
            } else {
                return null;
            }
        } catch (HttpClientErrorException.Unauthorized | HttpClientErrorException.Forbidden |
                 HttpServerErrorException.ServiceUnavailable | HttpClientErrorException.TooManyRequests |
                 ResourceAccessException e) {
            log.error("IntegrationService::postEmployee Retryable Exception {} ", e.getMessage());
            throw e;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("IntegrationService::postEmployee Non-Retryable Exception {} ", e.getMessage());
            return serverResponse.apply(null, e.getMessage());
        } catch (Exception e) {
            log.error("IntegrationService::postEmployee Exception {} ", e.getMessage());
            return serverResponse.apply(null, e.getMessage());
        }
    }

    public ResponseBean<Employee> exceededRateLimitFallbackPostEmployee(RequestNotPermitted requestNotPermitted) {
        log.error("IntegrationService:: exceededRateLimitFallbackPostEmployee e {}", requestNotPermitted.getMessage());
        return this.serverResponse.apply(null, requestNotPermitted.getMessage());
    }

    public ResponseBean<Employee> circuitBreakerFallbackPostEmployee(CallNotPermittedException callNotPermittedException) {
        log.error("IntegrationService:: circuitBreakerFallbackPostEmployee e {}", callNotPermittedException.getMessage());
        return serverResponse.apply(null, callNotPermittedException.getMessage());
    }

    private final BiFunction<ResponseEntity<ResponseBean<Employee>>, String, ResponseBean<Employee>> serverResponse = (data, e) -> {
        ResponseBean responseBean = new ResponseBean();
        responseBean.setDate(data);
        responseBean.setMessage(e);
        return responseBean;
    };

}
