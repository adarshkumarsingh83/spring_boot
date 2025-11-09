package com.espark.adarsh.store;

import com.espark.adarsh.bean.JobDetail;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component
public class DataStore {

    static final Map<String, JobDetail> executingStore = new HashMap<>();
    static final Map<String, JobDetail> completedStore = new HashMap<>();
    static final Map<String, JobDetail> failedStore = new HashMap<>();


    public Supplier<Map<String, JobDetail>> executingStoreSupplier = () -> {
        return executingStore;
    };

    public Supplier<Map<String, JobDetail>> failedStoreSupplier = () -> {
        return failedStore;
    };

    public Supplier<Map<String, JobDetail>> completedStoreSupplier = () -> {
        return completedStore;
    };

    public final Function<String, Optional<JobDetail>> jobExistInExecutingStore = (jobName) -> {
        return executingStore.entrySet()
                .stream()
                .filter(jobEntry -> jobEntry.getValue().getJobName().equals(jobName))
                .map(Map.Entry::getValue)
                .findFirst();
    };

    public final Function<String, Optional<JobDetail>> jobExistInCompleteStore = (jobName) -> {
        return completedStore.entrySet()
                .stream()
                .filter(jobEntry -> jobEntry.getValue().getJobName().equals(jobName))
                .map(Map.Entry::getValue)
                .findFirst();
    };

    public final Function<String, Optional<JobDetail>> jobExistInFailedStore = (jobName) -> {
        return failedStore.entrySet()
                .stream()
                .filter(jobEntry -> jobEntry.getValue().getJobName().equals(jobName))
                .map(Map.Entry::getValue)
                .findFirst();
    };

    public final Predicate<JobDetail> checkJobExistInStore = (jobDetail -> {
        return executingStore.containsKey(jobDetail.getJobName());
    });

    public final Predicate<List<String>> dependentJobExist = (jobs) -> {
        return jobs.parallelStream().anyMatch(e -> {
            return jobExistInExecutingStore.apply(e).isPresent();
        });
    };
}
