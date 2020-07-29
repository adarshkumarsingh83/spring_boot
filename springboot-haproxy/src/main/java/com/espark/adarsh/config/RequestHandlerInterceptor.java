/**
 *
 */
package com.espark.adarsh.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Component
public class RequestHandlerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        log.debug("label=RequestHandlerInterceptor preHandle() requestUrl={},requestMethod={}, requestHeader={} ",
                request.getRequestURL(), request.getMethod(), request.getHeaderNames());
        return true;
    }
}
