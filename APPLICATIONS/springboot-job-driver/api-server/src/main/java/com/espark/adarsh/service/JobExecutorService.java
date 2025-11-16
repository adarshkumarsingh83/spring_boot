package com.espark.adarsh.service;


import com.espark.adarsh.entities.JobDetail;
import com.espark.adarsh.entities.JobStatus;
import com.espark.adarsh.function.FunctionalTask;
import com.espark.adarsh.repository.JobRepository;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Slf4j
@Getter
@Component
public class JobExecutorService {


    @Value("${job.config.job-max-thread}")
    final Integer maxSize = 5;

    private JobRepository jobRepository;

    private ScheduledExecutorService scheduledExecutorService;

    static final ConcurrentLinkedQueue<JobDetail> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    public JobExecutorService(JobRepository jobRepository) {
        this.jobRepository = jobRepository;
    }

    @PostConstruct
    public void init() {
        scheduledExecutorService = Executors.newScheduledThreadPool(maxSize);
        jobsExecutors.executeTask();
    }

    final FunctionalTask jobsExecutors = () -> {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            CompletableFuture.runAsync(() -> {

                this.jobRepository.findByJobStatusIn(List.of(JobStatus.IN_QUEUE.getStatus()))
                        .stream()
                        .map(jobDetail -> {
                            jobDetail.setJobStatus(JobStatus.STARTING);
                            jobDetail.setJobMessage("job is executing ");
                            jobDetail.setStartedOn(LocalDateTime.now());
                            jobDetail = jobRepository.save(jobDetail);
                            log.info("Starting Job Details {}", jobDetail);
                            return jobDetail;
                        }).toList();

                this.jobRepository.findByJobStatusIn(List.of(JobStatus.EXECUTING.getStatus()))
                        .stream()
                        .map(jobDetail -> {
                            if (LocalDateTime.now().isAfter(jobDetail.getExpectedCompletion())) {
                                jobDetail.setJobStatus(JobStatus.COMPLETED);
                                jobDetail.setJobMessage("job is completed ");
                                jobDetail.setCompletedOn(LocalDateTime.now());
                                jobDetail = jobRepository.save(jobDetail);
                                log.info("Completed Job Details {}", jobDetail);
                                return jobDetail;
                            } else {
                                jobDetail.setJobStatus(JobStatus.EXECUTING);
                                jobDetail.setJobMessage("job is executing ");
                                jobDetail.setLastIteration(LocalDateTime.now());
                                jobDetail = jobRepository.save(jobDetail);
                                log.info("Executing Job Details {}", jobDetail);
                                return jobDetail;
                            }
                        }).toList();

                this.jobRepository.findByJobStatusIn(List.of(JobStatus.REQUEST_FOR_ABORT.getStatus()))
                        .stream()
                        .map(jobDetail -> {
                            jobDetail.setJobStatus(JobStatus.ABORTED);
                            jobDetail.setJobMessage("job is aborted ");
                            jobDetail.setCompletedOn(LocalDateTime.now());
                            jobDetail = jobRepository.save(jobDetail);
                            log.info("Aborted Job Details {}", jobDetail);
                            return jobDetail;
                        }).toList();

            });
        }, 0, 30, TimeUnit.SECONDS);
    };

}
