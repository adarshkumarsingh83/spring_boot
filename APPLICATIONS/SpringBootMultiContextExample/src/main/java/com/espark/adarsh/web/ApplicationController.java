package com.espark.adarsh.web;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/app")
public class ApplicationController {

    @Value("${environments.${spring.profiles.active}.name}")
    private String profileName;

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public @ResponseBody
    Map<String,Object> getApplicationProfile(){
        return new HashMap<String,Object>(){
            {
                put("profile",profileName);
                put("msg","welcome to the application");
            }
        };
    }
}
