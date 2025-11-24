package com.espark.adarsh.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ApiResponse <T> {
    T data;
    String message;
    int statusCode;
}
