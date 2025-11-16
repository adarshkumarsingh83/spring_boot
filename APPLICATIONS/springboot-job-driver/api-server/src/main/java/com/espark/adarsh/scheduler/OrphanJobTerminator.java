package com.espark.adarsh.scheduler;


import com.espark.adarsh.config.JobsConfigDetails;
import com.espark.adarsh.config.OnRequestJobConfig;
import com.espark.adarsh.config.ScheduleJobConfig;

import com.espark.adarsh.entities.JobDetail;
import com.espark.adarsh.entities.JobStatus;
import com.espark.adarsh.repository.JobRepository;
import jakarta.annotation.PostConstruct;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@Component
public class OrphanJobTerminator {

    private final JobRepository jobRepository;
    private final JobsConfigDetails jobsConfigDetails;
    private final ScheduleJobConfig scheduleJobConfig;

    private Map<String, List<OnRequestJobConfig.OrphanJobConfig>> orphanJobConfigMap = new HashMap<>();

    public OrphanJobTerminator(JobRepository jobRepository,
                               JobsConfigDetails jobsConfigDetails,
                               ScheduleJobConfig scheduleJobConfig) {
        this.jobRepository = jobRepository;
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
        log.info("Orphan Job Config Map {}", orphanJobConfigMap);
    }

    @Scheduled(cron = "${job.config.on-schedule-job-types.orphan-cleanup.corn}")
    public void executeOrphanCleanup() {

        log.info("Orphan Job cleanup process executing");

        List<JobDetail> cleanup =  orphanJobConfigMap.entrySet()
                .stream()
                .flatMap(entry -> entry.getValue().stream()
                        .flatMap(orphanConfig -> jobRepository.findByJobNameInAndJobStatusIn(
                                List.of(entry.getKey()), orphanConfig.getState()).stream()))
                .filter(Objects::nonNull)
                .filter(jobDetail -> LocalDateTime.now().isAfter(jobDetail.getExpectedCompletion()))
                .peek(jobDetail -> {
                    jobDetail.setJobStatus(JobStatus.FORCED_ABORTED_CLEANUP);
                    jobDetail.setCompletedOn(LocalDateTime.now());
                    jobRepository.save(jobDetail);
                })
                .collect(Collectors.toList());

        log.info("job cleaned {}", cleanup);
    }

}
