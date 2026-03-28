package com.espark.adarsh.evernt;

import lombok.Data;

@Data
public class StartJobEvent implements JobEvent{

    @Override
    public JobState getJobState() {
        return JobState.STARTJOB;
    }

    @Override
    public Boolean startJob() {
        return true;
    }
}
