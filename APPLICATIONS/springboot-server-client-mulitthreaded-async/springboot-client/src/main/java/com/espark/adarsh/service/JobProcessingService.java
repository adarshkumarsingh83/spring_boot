package com.espark.adarsh.service;

import com.espark.adarsh.bean.User;
import com.espark.adarsh.evernt.JobEventPublisher;
import com.espark.adarsh.evernt.StartJobEvent;
import com.espark.adarsh.evernt.StopJobEvent;
import com.espark.adarsh.repository.DataRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

@Slf4j
@Service
public class JobProcessingService {

    private  final ApplicationService applicationService;
    private final JobEventPublisher jobEventPublisher;
    private final DataRepository dataRepository;
    private static final Random random = new Random();

    public JobProcessingService(ApplicationService applicationService,
                                JobEventPublisher jobEventPublisher,
                                DataRepository dataRepository) {
        this.applicationService = applicationService;
        this.jobEventPublisher = jobEventPublisher;
        this.dataRepository = dataRepository;
    }

    @Async
    public void doProcessJob(int number) {
        int i = random.nextInt(100);
        // todo throw event to start the data processing for queue
       // jobEventPublisher.publishEvent(new StartJobEvent());
        log.info("Started processing for {} users",number);
        while(i > 0){
            List<User> dataList = dataRepository.generateData("_"+i+"_",number);
            applicationService.setData(dataList);
          i--;
        }
        // todo throw event to change the flag to process data
      //  jobEventPublisher.publishEvent(new StopJobEvent());
        log.info("Completed processing for {} users",number);
    }
}
