package com.espark.adarsh.bean;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;
import lombok.*;

import java.io.Serializable;


@Getter
@Setter
@ToString
@JsonRootName(value = "messageBean")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class MessageBean implements Serializable {

    @JsonProperty("id")
    private Long id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("timeStamp")
    private Long timeStamp;
    @JsonProperty("message")
    private String message;

    @JsonCreator
    public MessageBean() {
    }

}