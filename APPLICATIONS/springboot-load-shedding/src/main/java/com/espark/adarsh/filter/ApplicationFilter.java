package com.espark.adarsh.filter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicInteger;

@Component
public class ApplicationFilter implements Filter {

    @Value("${spring.request.limit}")
    private int maxRequests;

    @Value("${spring.request.message}")
    private String message;

    private AtomicInteger currentRequests = new AtomicInteger(0);

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        byte[] byteMessage = message.getBytes(StandardCharsets.UTF_8);
        if (currentRequests.get() >= maxRequests) {
            HttpServletResponse response = (HttpServletResponse) servletResponse;
            response.setStatus(HttpServletResponse.SC_SERVICE_UNAVAILABLE);
            response.getOutputStream().write(byteMessage);
            return;
        }
        currentRequests.incrementAndGet();
        try {
            filterChain.doFilter(servletRequest, servletResponse);
        } finally {
            currentRequests.decrementAndGet();
        }
    }
}
