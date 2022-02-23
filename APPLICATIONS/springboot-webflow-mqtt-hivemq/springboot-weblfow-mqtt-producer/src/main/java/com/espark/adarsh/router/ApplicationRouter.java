package com.espark.adarsh.router;

import com.espark.adarsh.handler.MessageHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Component
public class ApplicationRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(MessageHandler messageHandler) {
        return RouterFunctions.route()
                .GET("/router/publish/{message}", messageHandler::publishMessage)
                .build();

    }
}
