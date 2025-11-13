package com.espark.adarsh.scheduler;


import com.espark.adarsh.bean.JobDetail;
import com.espark.adarsh.config.JobsConfigDetails;
import com.espark.adarsh.config.OnRequestJobConfig;
import com.espark.adarsh.config.ScheduleJobConfig;
import com.espark.adarsh.store.DataStore;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrphanJobTerminator {

    private final DataStore dataStore;
    private final JobsConfigDetails jobsConfigDetails;
    private final ScheduleJobConfig scheduleJobConfig;

    private Map<String, List<OnRequestJobConfig.OrphanJobConfig>> orphanJobConfigMap = new HashMap<>();

    public OrphanJobTerminator(DataStore dataStore,
                               JobsConfigDetails jobsConfigDetails,
                               ScheduleJobConfig scheduleJobConfig) {
        this.dataStore = dataStore;
        this.jobsConfigDetails = jobsConfigDetails;
        this.scheduleJobConfig = scheduleJobConfig;
    }


    @PostConstruct
    public void init() {
        ScheduleJobConfig.ScheduleJob scheduleJob = scheduleJobConfig.getOnScheduleJobTypes().get("orphan-cleanup");
        orphanJobConfigMap = jobsConfigDetails.getOnRequestJobTypes()
                .entrySet()
                .stream()
                .filter(e -> scheduleJob.getJobTypes().contains(e.getKey()))
                .collect(Collectors.toMap(Map.Entry::getKey,
                        entry -> entry.getValue().getOrphan()));
    }

    @Scheduled(cron = "${job.config.on-schedule-job-types.orphan-cleanup.corn}")
    public void executeOrphanCleanup() {
        log.info("Orphan Job cleanup process executing");
        List<String> cleanup = orphanJobConfigMap.entrySet()
                .stream()
                .filter(entry -> {
                    return dataStore.getExecutingStoreSupplier().get().containsKey(entry.getKey());
                }).filter(entry -> {
                    JobDetail jobDetail = dataStore.getExecutingStoreSupplier().get().get(entry.getKey());
                    return LocalDateTime.now().isAfter(jobDetail.getExpectedCompletion());
                }).map(entry -> {
                    JobDetail jobDetail = dataStore.getExecutingStoreSupplier().get().get(entry.getKey());
                    jobDetail.setJobStatus("FORCED-ABORTED-CLEANUP");
                    jobDetail.setCompletedOn(LocalDateTime.now());
                    return jobDetail.getJobName();
                }).toList();
        log.info("job cleaned {}", cleanup);
    }

}
