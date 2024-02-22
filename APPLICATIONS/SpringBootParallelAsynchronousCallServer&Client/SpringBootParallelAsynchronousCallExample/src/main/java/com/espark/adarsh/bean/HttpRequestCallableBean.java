package com.espark.adarsh.bean;

import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

public class HttpRequestCallableBean<T> {

    private T bodyData;

    private String url;

    private Map<String,String> headers;

    private RequestMethod httpMethod;

    private Map<String,Object> requestParam;

    private Map<String,Object> queryParam;

    public HttpRequestCallableBean() {
    }

    public HttpRequestCallableBean(String url, RequestMethod httpMethod) {
        this.url = url;
        this.httpMethod = httpMethod;
    }

    public T getBodyData() {
        return bodyData;
    }

    public void setBodyData(T bodyData) {
        this.bodyData = bodyData;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public void setHeaders(Map<String, String> headers) {
        this.headers = headers;
    }

    public RequestMethod getHttpMethod() {
        return httpMethod;
    }

    public void setHttpMethod(RequestMethod httpMethod) {
        this.httpMethod = httpMethod;
    }

    public Map<String, Object> getRequestParam() {
        return requestParam;
    }

    public void setRequestParam(Map<String, Object> requestParam) {
        this.requestParam = requestParam;
    }

    public Map<String, Object> getQueryParam() {
        return queryParam;
    }

    public void setQueryParam(Map<String, Object> queryParam) {
        this.queryParam = queryParam;
    }
}
