package com.espark.adarsh.feign;

import feign.RequestLine;

import java.util.Map;

public interface ExternalService {

    @RequestLine("GET /messages")
    public Map<String,String> getMessage();
}
