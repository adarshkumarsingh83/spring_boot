package com.espark.adarsh.client.bean;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class AbstractApiDetails {

    String serverUrl;
    String apiUrl;
    String httpMethod;
    String requestBody;
    String requestTransformer;
    String responseTransformer;
    String resolver;
    String queryParams;
    String authKey;
    ParameterizedTypeReference parameterizedTypeReference;
    Map<String, String> headers = new HashMap<>();
    Map<String, Objects> requestParam = new HashMap<>();

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    public HttpMethod getHttpMethod() {
        return HttpMethod.valueOf(httpMethod);
    }

    public void setHttpMethod(String httpMethod) {
        this.httpMethod = httpMethod;
    }

    public String getRequestBody() {
        return requestBody;
    }

    public void setRequestBody(String requestBody) {
        this.requestBody = requestBody;
    }

    public String getRequestTransformer() {
        return requestTransformer;
    }

    public void setRequestTransformer(String requestTransformer) {
        this.requestTransformer = requestTransformer;
    }

    public String getResponseTransformer() {
        return responseTransformer;
    }

    public void setResponseTransformer(String responseTransformer) {
        this.responseTransformer = responseTransformer;
    }

    public String getResolver() {
        return resolver;
    }

    public void setResolver(String resolver) {
        this.resolver = resolver;
    }

    public String getQueryParams() {
        return queryParams;
    }

    public void setQueryParams(String queryParams) {
        this.queryParams = queryParams;
    }

    public String getAuthKey() {
        return authKey;
    }

    public void setAuthKey(String authKey) {
        this.authKey = authKey;
    }

    public ParameterizedTypeReference getParameterizedTypeReference() {
        return parameterizedTypeReference;
    }

    public void setParameterizedTypeReference(ParameterizedTypeReference parameterizedTypeReference) {
        this.parameterizedTypeReference = parameterizedTypeReference;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public Map<String, Objects> getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(Map<String, Objects> requestParam) {
        this.requestParam = requestParam;
    }

    public HttpHeaders getHeaders(){
        HttpHeaders httpHeaders = new org.springframework.http.HttpHeaders();
        headers.entrySet().stream().forEach(entry -> httpHeaders.add(entry.getKey(),entry.getValue()));
        return httpHeaders;
    }
}
