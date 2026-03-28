package com.espark.adarsh.evernt;

public enum JobState {

    NOJOB("NOJOB"),STARTJOB("STARTJOB"),STOPJOB("STOPJOB"),ABORTJOB("ABORTJOB");

    String value;

    JobState(String value){
        this.value = value;
    }

    public String getValue(){
        return this.value;
    }
}
