package com.espark.adarsh.web;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ApplicationController {

    @GetMapping("/list")
    List<String> getMessages(){
         return Arrays.asList("adarsh", "kumar", "radha", "singh");
     }
}
