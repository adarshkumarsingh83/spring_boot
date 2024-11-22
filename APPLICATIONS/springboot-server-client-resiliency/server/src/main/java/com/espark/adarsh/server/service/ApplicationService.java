package com.espark.adarsh.server.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.function.Function;
import java.util.function.Predicate;


@Service
public class ApplicationService {

    public Function<String, ResponseEntity<String>> processRequest = (request) -> {
        return switch (request) {
            case "100" -> ResponseEntity.status(100).body("100 server status is up");
            case "300" -> ResponseEntity.status(300).body("300 status");
            case "400" -> ResponseEntity.status(400).body("400 status");
            case "401" -> ResponseEntity.status(401).body("401 status");
            case "403" -> ResponseEntity.status(403).body("403 status");
            case "500" -> ResponseEntity.status(500).body("500 status");
            case "503" -> ResponseEntity.status(503).body("503 status");
            default -> ResponseEntity.status(isNumber.test(request)? Integer.parseInt(request): 200 ).body("welcome in the server ");
        };
    };

    static final Predicate<String> isNumber = (data)->{
            return data.matches("-?\\d+(\\.\\d+)?");  //match a number with optional '-' and decimal.
    };

}
