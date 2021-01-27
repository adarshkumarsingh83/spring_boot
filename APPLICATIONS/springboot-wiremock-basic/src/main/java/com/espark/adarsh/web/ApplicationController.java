package com.espark.adarsh.web;

import com.espark.adarsh.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    ApplicationService applicationService;

    @GetMapping("/data")
    public String getData() {
        return this.applicationService.getData();
    }

    @PostMapping("/data")
    public String setData(@RequestBody String data) {
        return this.applicationService.setData(data);
    }
}
