package com.espark.adarsh.bean;

import lombok.Data;

@Data
public class ApiConfig {
    String url;
    String httpMethod;
    boolean enable;
    Integer threshold;
}
