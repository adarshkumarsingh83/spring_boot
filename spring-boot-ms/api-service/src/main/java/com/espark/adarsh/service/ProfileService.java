package com.espark.adarsh.service;

import com.espark.adarsh.bean.PersonProfile;
import com.espark.adarsh.configuration.FeignConfiguration;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.Set;

@FeignClient(serviceId = "${feign}"
        , fallback = ProfileServiceImpl.class
        , configuration = FeignConfiguration.class)
public interface ProfileService {


    @RequestMapping(value = "/profile",method = RequestMethod.POST, consumes = "application/json")
    public void save(@RequestBody PersonProfile personProfile);

    @RequestMapping(value = "/profile",method = RequestMethod.GET)
    public @ResponseBody
    Set<PersonProfile> getProfiles();
}
