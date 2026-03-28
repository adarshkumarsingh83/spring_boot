package com.espark.adarsh.evernt;

import lombok.Data;

@Data
public class StopJobEvent implements JobEvent{

    @Override
    public JobState getJobState() {
        return JobState.STOPJOB;
    }

    @Override
    public Boolean stopJob() {
        return true;
    }
}
