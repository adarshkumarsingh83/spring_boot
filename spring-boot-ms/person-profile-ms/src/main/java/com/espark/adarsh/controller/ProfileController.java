package com.espark.adarsh.controller;

import com.espark.adarsh.bean.PersonProfile;
import com.espark.adarsh.service.ProfileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ProfileController {

    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private ProfileService profileService;

    @RequestMapping(value = "/profile",method = RequestMethod.POST)
    public void save(@RequestBody PersonProfile personProfile){
        logger.info("ProfileController save()");
        this.profileService.save(personProfile);
    }

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public @ResponseBody Set<PersonProfile> getProfiles(){
        logger.info("ProfileController getProfiles()");
        return this.profileService.getProfiles();
    }

}