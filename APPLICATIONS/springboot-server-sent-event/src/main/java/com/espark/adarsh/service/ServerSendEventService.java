package com.espark.adarsh.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.function.Consumer;

@Slf4j
@Service
public class ServerSendEventService {

    private BusService busService;
    private TrainService trainService;
    private FlightService flightService;

    public ServerSendEventService(BusService busService, TrainService trainService, FlightService flightService) {
        this.busService = busService;
        this.trainService = trainService;
        this.flightService = flightService;
    }

    public Consumer<SseEmitter> sendTransportStatus = (SseEmitter sseEmitter) -> {
        log.info("ServerSendEventService sendTransportStatus:");
        busService.publishEvent(sseEmitter);
        trainService.publishEvent(sseEmitter);
        flightService.publishEvent(sseEmitter);
        log.info("ServerSendEventService sendTransportStatus: invoked all services");
    };
}
