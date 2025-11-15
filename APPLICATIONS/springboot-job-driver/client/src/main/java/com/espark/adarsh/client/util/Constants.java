package com.espark.adarsh.client.util;


public final class Constants {


    public static final String MONITOR_JOB_EXECUTION = "monitor-job-execution";

    private Constants(){
    }

    public static final String POST_API_CIRCUIT_BREAKER ="postApiCircuitBreaker";
    public static final String PUT_API_CIRCUIT_BREAKER ="putApiCircuitBreaker";
    public static final String GET_API_CIRCUIT_BREAKER ="getApiCircuitBreaker";

    public static final String POST_API_RATE_LIMITER ="postApiRateLimiter";
    public static final String PUT_API_RATE_LIMITER ="putApiRateLimiter";
    public static final String GET_API_RATE_LIMITER ="getApiRateLimiter";

    public static final String POST_API_RETRY ="postApiRetry";
    public static final String PUT_API_RETRY ="putApiRetry";
    public static final String GET_API_RETRY ="getApiRetry";

    public static final String GET ="GET";
    public static final String POST ="POST";
    public static final String PUT ="PUT";

    public static final String WAITING ="WAITING";
    public static final String IN_QUEUE ="IN-QUEUE";
    public static final String STARTING ="STARTING";
    public static final String EXECUTING ="EXECUTING";
    public static final String FAILED ="FAILED";
    public static final String COMPLETED ="COMPLETED";

    public static final String START = "start";
    public static final String STATUS = "status";
    public static final String ABORT = "abort";
    public static final String JOB_TYPE = "jobType";
    public static final String OPERATION = "operation";
    public static final String EXECUTE = "execute";
    public static final String EXECUTE_SEQUENTIAL = "execute-sequential";
    public static final String EXECUTE_PARALLEL = "execute-parallel";
    public static final String REQUEST_FOR_ABORT = "request-for-abort";
}
