package com.espark.adarsh.client.service.integration;


import com.espark.adarsh.client.util.Constants;
import io.github.resilience4j.circuitbreaker.CallNotPermittedException;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.ratelimiter.RequestNotPermitted;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import io.github.resilience4j.retry.annotation.Retry;
import com.espark.adarsh.client.bean.AbstractApiDetails;
import com.espark.adarsh.client.bean.ApiResponse;
import com.espark.adarsh.client.config.ApiRestTemplateFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import java.util.function.BiFunction;

@Slf4j
@Service
public class HttpGetApiIntegrationServiceImpl<T> implements ApiIntegrationService<T> {

    private final ApiRestTemplateFactory apiRestTemplateFactory;

    public HttpGetApiIntegrationServiceImpl(ApiRestTemplateFactory apiRestTemplateFactory) {
        this.apiRestTemplateFactory = apiRestTemplateFactory;
    }

    @Retry(name = Constants.GET_API_RETRY)
    @CircuitBreaker(name = Constants.GET_API_CIRCUIT_BREAKER, fallbackMethod = "executeCircuitBreakerGet")
    @RateLimiter(name = Constants.GET_API_RATE_LIMITER, fallbackMethod = "executeRateLimiterFallbackGet")
    public ApiResponse<T> getApiCallExecution(AbstractApiDetails apiDetails, String jobName) {
        try {

            final String url = apiDetails.getServerUrl() + apiDetails.getApiUrl();
            HttpHeaders httpHeaders = apiDetails.getHeaders();
            HttpEntity<String> httpEntity = new HttpEntity<>(apiDetails.getRequestBody(), httpHeaders);
            RestTemplate restTemplate = apiRestTemplateFactory.restTemplate.apply(apiDetails.getAuthKey());
            ResponseEntity<T> statusResponseEntity = restTemplate.exchange(url, apiDetails.getHttpMethod(),
                    httpEntity, apiDetails.getParameterizedTypeReference());
            log.info("getApiCallExecution request send from {} for url {}", jobName, url);
           if(statusResponseEntity.getStatusCode().equals(HttpStatus.OK)){
               ApiResponse<T> apiResponse = new ApiResponse<>();
               apiResponse.setData(statusResponseEntity.getBody());
               apiResponse.setStatus(Boolean.TRUE);
               return apiResponse;
           }
           return errorResponse.apply(statusResponseEntity.getBody(),"HttpStatusCode from Server "+statusResponseEntity.getStatusCode());
        } catch (HttpClientErrorException.Unauthorized | HttpClientErrorException.Forbidden |
                 HttpServerErrorException.ServiceUnavailable | HttpClientErrorException.TooManyRequests |
                 ResourceAccessException e) {
            log.error("GetApiCallExecution Retryable {}", e.getMessage());
            throw e;
        } catch (HttpClientErrorException | HttpServerErrorException e) {
            log.error("GetApiCallExecution Non-Retryable {}", e.getMessage());
            return errorResponse.apply(null, e.getMessage());
        } catch (Exception e) {
            log.error("GetApiCallExecution Exception Occurred {}", e.getMessage());
            return errorResponse.apply(null, e.getMessage());
        }
    }

    public ApiResponse<T> executeCircuitBreakerGet(CallNotPermittedException callNotPermittedException) {
        log.error("HttpGetApiIntegrationServiceImpl:: executeCircuitBreakerGet {}", callNotPermittedException.getMessage());
        return errorResponse.apply(null, callNotPermittedException.getMessage());
    }

    public ApiResponse<T> executeRateLimiterFallbackGet(RequestNotPermitted requestNotPermitted) {
        log.error("HttpGetApiIntegrationServiceImpl:: executeRateLimiterFallbackGet {}", requestNotPermitted.getMessage());
        return errorResponse.apply(null, requestNotPermitted.getMessage());
    }

    private final BiFunction<T, String, ApiResponse<T>> errorResponse = (T data, String e) -> {
        ApiResponse<T> apiResponse = new ApiResponse<>();
        apiResponse.setData(data);
        apiResponse.setMessage(e);
        apiResponse.setStatus(Boolean.FALSE);
        return apiResponse;
    };
}
