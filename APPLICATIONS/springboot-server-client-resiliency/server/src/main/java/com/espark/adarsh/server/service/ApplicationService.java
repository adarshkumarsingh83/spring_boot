package com.espark.adarsh.server.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;


@Service
public class ApplicationService {

    public Function<String, ResponseEntity<String>> processRequest = (request) -> {
        return switch (request) {
            case "100" -> ResponseEntity.status(100).body("100 server status is up");
            case "300" -> ResponseEntity.status(300).body("300 status");
            case "400" -> ResponseEntity.status(400).body("400 status");
            case "500" -> ResponseEntity.status(500).body("500 status");
            default -> ResponseEntity.ok().body("welcome in the server ");
        };
    };

}
