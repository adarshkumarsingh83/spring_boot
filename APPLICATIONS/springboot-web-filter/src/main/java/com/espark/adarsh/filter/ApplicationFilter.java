package com.espark.adarsh.filter;

import com.espark.adarsh.bean.ApiConfig;
import com.espark.adarsh.bean.RateLimitConfig;
import com.espark.adarsh.predicate.EsparkPredicate;
import com.espark.adarsh.util.MessageStore;
import com.espark.adarsh.util.RateLimitCache;
import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.function.Function;


@Slf4j
public class ApplicationFilter implements Filter {

    RateLimitConfig rateLimitConfig;

    RateLimitCache rateLimitCache;

    MessageStore messageStore;

    public ApplicationFilter(RateLimitCache rateLimitCache, RateLimitConfig rateLimitConfig, MessageStore messageStore) {
        this.rateLimitCache = rateLimitCache;
        this.rateLimitConfig = rateLimitConfig;
        this.messageStore = messageStore;
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpServletResponse = (HttpServletResponse) servletResponse;
        log.info("request method {} url{}", httpServletRequest.getMethod(), httpServletRequest.getRequestURI());

        if (this.rateLimitProcessor.apply(httpServletRequest)) {
            filterChain.doFilter(servletRequest, servletResponse);
        } else {
            httpServletResponse.setStatus(429);
            httpServletResponse.getOutputStream().write(this.messageStore.getMessage("threshold").getBytes());
        }

    }

    public Function<HttpServletRequest, Boolean> rateLimitProcessor = (request) -> {
        String url = request.getRequestURI();
        String httpMethod = request.getMethod();
        ApiConfig apiConfig = this.rateLimitConfig.getApiConfig()
                .stream()
                .filter(api -> this.requestPredicate.test(api, url, httpMethod))
                .findFirst()
                .orElse(null);
        return this.rateLimitCache.checkRequestLimit.test(apiConfig, url, httpMethod);
    };

    public EsparkPredicate<ApiConfig, String, String> requestPredicate = ((apiConfig, url, httpMethod) -> {
        return apiConfig != null && apiConfig.getUrl().equals(url)
                && apiConfig.getHttpMethod().equalsIgnoreCase(httpMethod);
    });
}
