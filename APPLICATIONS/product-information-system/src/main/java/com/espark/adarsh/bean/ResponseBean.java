package com.espark.adarsh.bean;


import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import org.springframework.http.HttpStatus;

@JsonPropertyOrder({
        "data",
        "exception",
        "httpStatus",
        "message",
})
public class ResponseBean<D,M> {

    private D data;
    private String exception;
    private HttpStatus httpStatus;
    private M message;

    public ResponseBean() {
    }

    public ResponseBean(D data, HttpStatus httpStatus, M message) {
        this.data = data;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public ResponseBean(D data, String exception, HttpStatus httpStatus, M message) {
        this.data = data;
        this.exception = exception;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public D getData() {
        return data;
    }

    public void setData(D data) {
        this.data = data;
    }

    public String getException() {
        return exception;
    }

    public void setException(String exception) {
        this.exception = exception;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
    }

    public M getMessage() {
        return message;
    }

    public void setMessage(M message) {
        this.message = message;
    }
}
