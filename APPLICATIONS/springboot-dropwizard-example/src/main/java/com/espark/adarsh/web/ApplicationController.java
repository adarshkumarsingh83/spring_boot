package com.espark.adarsh.web;

import com.codahale.metrics.annotation.Timed;
import com.espark.adarsh.service.WishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    WishService wishService;

    @Timed
    @GetMapping("/wish/{name}")
    public ResponseEntity<Map<String, String>> wish(@PathVariable("name") String name) {
        return ResponseEntity.ok(wishService.wish(name));
    }

}