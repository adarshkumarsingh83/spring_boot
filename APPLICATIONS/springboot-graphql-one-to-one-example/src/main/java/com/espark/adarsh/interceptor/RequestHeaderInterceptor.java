package com.espark.adarsh.interceptor;

import org.springframework.graphql.server.WebGraphQlInterceptor;
import org.springframework.graphql.server.WebGraphQlRequest;
import org.springframework.graphql.server.WebGraphQlResponse;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.Collections;

@Component
public class RequestHeaderInterceptor implements WebGraphQlInterceptor {

    @Override
    public Mono<WebGraphQlResponse> intercept(WebGraphQlRequest request, Chain chain) {
        String value = request.getHeaders().getFirst("espark");
        if (value != null) {
            request.configureExecutionInput((executionInput, builder) ->
                    builder.graphQLContext(Collections.singletonMap("espark", value)).build());
        }
        return chain.next(request);
    }
}