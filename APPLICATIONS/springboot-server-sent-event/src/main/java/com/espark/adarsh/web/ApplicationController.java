package com.espark.adarsh.web;

import com.espark.adarsh.service.ServerSendEventService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;

@Slf4j
@RestController
public class ApplicationController {

    private ServerSendEventService serverSendEventService;

    public ApplicationController(ServerSendEventService serverSendEventService) {
        this.serverSendEventService = serverSendEventService;
    }

    @GetMapping(value = "/transport/status", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Object getTransportStatus() throws IOException, InterruptedException {
        log.info("ApplicationController getTransportStatus:");
        SseEmitter sseEmitter = new SseEmitter(5000L);
        serverSendEventService.sendTransportStatus.accept(sseEmitter);
        return sseEmitter;
    }

}
