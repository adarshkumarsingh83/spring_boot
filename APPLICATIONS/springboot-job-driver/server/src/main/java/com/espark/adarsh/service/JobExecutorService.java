package com.espark.adarsh.service;


import com.espark.adarsh.bean.JobDetail;
import com.espark.adarsh.store.DataStore;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.Set;
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

    private DataStore dataStore;

    ScheduledExecutorService scheduledExecutorService;

    static final ConcurrentLinkedQueue<JobDetail> concurrentLinkedQueue = new ConcurrentLinkedQueue<>();

    public JobExecutorService(DataStore dataStore) {
        this.dataStore = dataStore;
    }

    @PostConstruct
    public void init() {
        scheduledExecutorService = Executors.newScheduledThreadPool(maxSize);
        jobsExecutors.accept(concurrentLinkedQueue);
    }

    public final Supplier<ConcurrentLinkedQueue<JobDetail>> concurrentLinkedQueueSupplier = () -> {
        return concurrentLinkedQueue;
    };

    public final Predicate<JobDetail> checkJobExistInQueue = (jobDetail) -> {
        return concurrentLinkedQueue.stream().anyMatch(e -> e.getJobName().equals(jobDetail.getJobName()));
    };

    final Predicate<ConcurrentLinkedQueue<JobDetail>> isEmpty = (concurrentLinkedQueue) -> {
        synchronized (concurrentLinkedQueue) {
            return concurrentLinkedQueue.isEmpty();
        }
    };

    final Consumer<ConcurrentLinkedQueue<JobDetail>> jobsExecutors = (concurrentLinkedQueue) -> {
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            CompletableFuture.runAsync(() -> {
                if (!isEmpty.test(concurrentLinkedQueue)) {
                    JobDetail jobDetail = concurrentLinkedQueue.poll();
                    log.info("Job Details Before Starting {}", jobDetail);
                    jobDetail.setStatus("STARTING");
                    jobDetail.setMessage("job is executing ");
                    dataStore.getExecutingStoreSupplier().get().put(jobDetail.getJobId(), jobDetail);
                } else if (!dataStore.getExecutingStoreSupplier().get().isEmpty()) {
                    log.info("executing jobs in job pools");
                    final Set<String> keySet = dataStore.getExecutingStoreSupplier().get().keySet();
                    keySet.stream().forEach(e -> {
                        JobDetail jobDetail = dataStore.getExecutingStoreSupplier().get().get(e);
                        log.info("Job Details while Executing {}", jobDetail);
                        final String status = jobDetail.getStatus();
                        switch (status) {
                            case "EXECUTING" -> {
                                if (LocalDateTime.now().isAfter(jobDetail.getExpectedCompletion())) {
                                    jobDetail.setStatus("COMPLETED");
                                    jobDetail.setMessage("job is completed ");
                                    jobDetail.setCompletedOn(LocalDateTime.now());
                                    dataStore.getCompletedStoreSupplier().get().put(jobDetail.getJobId(), jobDetail);
                                    dataStore.getExecutingStoreSupplier().get().remove(jobDetail.getJobId());
                                    log.info("Job Details After Completion {}", jobDetail);
                                }
                            }
                            case "ABORTED" -> {
                                jobDetail.setStatus("ABORTED");
                                jobDetail.setMessage("job is aborted ");
                                jobDetail.setCompletedOn(LocalDateTime.now());
                                dataStore.getFailedStoreSupplier().get().put(jobDetail.getJobId(), jobDetail);
                                dataStore.getExecutingStoreSupplier().get().remove(jobDetail.getJobId());
                                log.info("Job Details After Aborted {}", jobDetail);
                            }
                            case "STARTING" -> {
                                jobDetail.setStatus("EXECUTING");
                                jobDetail.setMessage("job is executing ");
                                jobDetail.setLastIteration(LocalDateTime.now());
                                dataStore.getExecutingStoreSupplier().get().put(jobDetail.getJobId(), jobDetail);
                                log.info("Job Details before Executing job {} threadName {}", jobDetail, Thread.currentThread().getName());
                            }
                        }
                    });
                }

            });
        }, 0, 30, TimeUnit.SECONDS);
    };

}
