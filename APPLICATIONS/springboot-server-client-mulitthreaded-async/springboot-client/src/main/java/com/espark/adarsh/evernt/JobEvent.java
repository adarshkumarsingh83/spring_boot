package com.espark.adarsh.evernt;

public interface JobEvent {

    default JobState getJobState(){
        return null;
    }

    default Boolean startJob(){
        return false;
    }

    default Boolean stopJob(){
        return  false;
    }

    default Boolean abortJob(){
        return false;
    }

}
