package com.espark.adarsh.evernt;

import lombok.Data;

@Data
public class AbortJobEvent implements JobEvent {

    @Override
    public JobState getJobState() {
        return JobState.ABORTJOB;
    }

    @Override
    public Boolean abortJob() {
        return true;
    }
}
