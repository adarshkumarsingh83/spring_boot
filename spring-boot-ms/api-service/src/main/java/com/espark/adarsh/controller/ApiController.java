package com.espark.adarsh.controller;

import com.espark.adarsh.bean.PersonProfile;
import com.espark.adarsh.service.IntegrationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/notifications")
public class ApiController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private IntegrationService integrationService;

    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public void save(@RequestBody PersonProfile personProfile){
        logger.info("ApiController save()");
         this.integrationService.save(personProfile);
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public @ResponseBody
    Set<PersonProfile> getProfiles(){
        logger.info("ApiController getProfiles()");
        return this.integrationService.getProfiles();
    }


}
