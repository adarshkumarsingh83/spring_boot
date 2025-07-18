package com.espark.adarsh.service;

import com.espark.adarsh.bean.TransportStatus;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.concurrent.CompletableFuture;

import static com.espark.adarsh.util.Constants.SERVER_SEND_EVENT_EXECUTOR;

@Slf4j
@Service
public class TrainService {

    @Async(SERVER_SEND_EVENT_EXECUTOR)
    public CompletableFuture<Void> publishEvent(SseEmitter sssEmitter) {

        log.info("TrainService publishEvent:");
        try {
            Thread.sleep(2000);
            sssEmitter.send(TransportStatus.builder()
                    .id("12")
                    .type("train-service")
                    .status("in-transit")
                    .from("dallas")
                    .destination("la")
                    .message("train is in transit")
                    .eventTime(LocalDateTime.now())
                    .build()
            );
            return CompletableFuture.completedFuture(null);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            log.error("TrainService Event publishing interrupted", e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            log.error("TrainService Event publishing interrupted", e);
            throw new RuntimeException(e);
        }
    }
}
