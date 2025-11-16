package com.espark.adarsh.entities;

public enum JobStatus {

    STARTING("STARTING"),
    EXECUTING("EXECUTING"),
    COMPLETED("COMPLETED"),
    ABORTED("ABORTED"),
    IN_QUEUE("IN-QUEUE"),
    REQUEST_FOR_ABORT("REQUEST-FOR-ABORT"),
    FORCED_ABORTED_CLEANUP("FORCED-ABORTED-CLEANUP"),
    WAITING("WAITING"),
    FAILED("FAILED");

    final String status;

     JobStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

}
