package com.espark.adarsh.service;

import com.espark.adarsh.bean.PersonProfile;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import rx.Observable;

import java.util.Set;

@Service
public class IntegrationService {

    private final Logger log = LoggerFactory.getLogger(this.getClass());

    @Autowired
    @Lazy
    private EurekaClient discoveryClient;

    @Autowired
    private ProfileService profileService;

    @HystrixCommand(groupKey = "tp-person-profile-ms", fallbackMethod = "statusNotFound")
    public InstanceInfo.InstanceStatus notificationsStatus() {
        return this.discoveryClient.getNextServerFromEureka("person-profile-ms", false)
                .getStatus();
    }

    public InstanceInfo.InstanceStatus statusNotFound() {
        return InstanceInfo.InstanceStatus.DOWN;
    }

    @HystrixCommand(groupKey = "tp-person-profile-ms", fallbackMethod = "profileAreDown")
    public Observable<String> statusPageUrl() {
        return Observable.just(
                this.discoveryClient.getNextServerFromEureka("person-profile-ms", false)
                        .getStatusPageUrl()
        );
    }

    public String profileAreDown() {
        return "profile service is down";
    }


    public void save(PersonProfile personProfile){
        log.info("IntegrationService save()");
        this.profileService.save(personProfile);
    }

    public Set<PersonProfile> getProfiles(){
        log.info("IntegrationService getProfiles()");
        return this.profileService.getProfiles();
    }
}
