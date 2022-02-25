package com.espark.adarsh.handler;

import com.espark.adarsh.service.MessageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MessageHandler {

    @Autowired
    MessageService messageService;

    public Mono<ServerResponse> publishMessage(ServerRequest serverRequest) {
        String data = serverRequest.pathVariable("message");
        String response = messageService.publishMessage(data);
        return ServerResponse.ok()
                .contentType(MediaType.TEXT_PLAIN)
                .body(Mono.just(response), String.class);
    }
}
