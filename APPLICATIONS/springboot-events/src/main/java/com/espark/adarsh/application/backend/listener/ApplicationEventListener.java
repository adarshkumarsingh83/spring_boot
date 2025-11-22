package com.espark.adarsh.application.backend.listener;

import com.espark.adarsh.application.backend.entities.Message;
import com.espark.adarsh.application.backend.repository.MessageRepository;
import com.espark.adarsh.application.frontend.event.EsparkEvent;
import jakarta.transaction.Transactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.modulith.events.ApplicationModuleListener;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.function.Function;


@Slf4j
@Component
@Transactional
public class ApplicationEventListener {

    private final MessageRepository messageRepository;

    public ApplicationEventListener(MessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    final Function<String, Message> createMessage = content -> {
        Message message = new Message();
        message.setContent(content);
        message.setCreatedAt(LocalDateTime.now());
        return message;
    };

    @ApplicationModuleListener
    public void onApplicationEvent(EsparkEvent event) {
        log.info("Event Received: {}", event.message());
        this.messageRepository.save(createMessage.apply(event.message()));
    }

}
