package com.espark.adarsh.config;

public class JobDetails {

    String maxRunTime;
    Boolean exceptions;

    public JobDetails() {
    }

    public String getMaxRunTime() {
        return maxRunTime;
    }

    public void setMaxRunTime(String maxRunTime) {
        this.maxRunTime = maxRunTime;
    }

    public Boolean getExceptions() {
        return exceptions;
    }

    public void setExceptions(Boolean exceptions) {
        this.exceptions = exceptions;
    }
}
