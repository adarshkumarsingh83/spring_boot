package com.espark.adarsh.exception;

import com.espark.adarsh.bean.JobConfig;
import com.espark.adarsh.bean.ResponseBean;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(InvalidJobRequestException.class)
    public ResponseBean<JobConfig> invalidJobRequest(InvalidJobRequestException invalidJobRequestException){
        ResponseBean<JobConfig> responseBean = new ResponseBean<>();
        responseBean.setMessage(invalidJobRequestException.getMessage());
        return responseBean;
    }

    @ExceptionHandler(Exception.class)
    public ResponseBean<JobConfig> exception(Exception exception){
        ResponseBean<JobConfig> responseBean = new ResponseBean<>();
        responseBean.setMessage(exception.getMessage());
        return responseBean;
    }
}
