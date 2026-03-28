package com.espark.adarsh.evernt;

import com.espark.adarsh.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class JobEventHandler {

    ApplicationService applicationService;

    public JobEventHandler(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @EventListener
    public void listenJobEvent(JobEvent jobEvent){
        log.info("JobEvent Received {}",jobEvent);
        JobState jobState =  jobEvent.getJobState();
        applicationService.setJobState(jobState);
        if(jobEvent.startJob()){
            applicationService.sendRequest();
        }
    }
}
