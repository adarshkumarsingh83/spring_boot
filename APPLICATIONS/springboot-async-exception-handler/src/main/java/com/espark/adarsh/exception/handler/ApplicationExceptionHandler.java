package com.espark.adarsh.exception.handler;

import com.espark.adarsh.exception.ApplicationException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.lang.reflect.Method;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler implements AsyncUncaughtExceptionHandler {


    @ExceptionHandler(value = {ApplicationException.class})
    public Map<String, String> handleApplicationException(HttpServletRequest request, ApplicationException ex) {
        log.error("ApplicationExceptionHandler {} ", ex.getMessage());
        String reqUrl = request.getRequestURL().toString();
        return new HashMap<>() {
            {
                put("exception", ex.getMessage());
                put("request-url", reqUrl);
            }
        };
    }


    @Override
    public void handleUncaughtException(Throwable ex, Method method, Object... params) {
        log.error("ApplicationExceptionHandler ASYNC {} ", ex.getMessage());
    }
}
