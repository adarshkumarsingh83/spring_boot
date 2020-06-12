package com.espark.adarsh;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by adarsh_k on 4/6/2017.
 */
@RestController
public class WelcomeRestService {

    @RequestMapping(value = "/welcome",method = RequestMethod.GET)
    @ResponseBody
    public Map<String,String> getWish(){
        return new HashMap<String,String>(){
            {
              put("name",System.getProperty("user.name"));
              put("message"," Welcome to the Esaprk");
            }
        };
    }
}
