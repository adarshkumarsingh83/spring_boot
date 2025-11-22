package com.espark.adarsh.application.frontend.event;

import org.springframework.modulith.events.Externalized;

@Externalized
public record EsparkEvent(String message) {
}
