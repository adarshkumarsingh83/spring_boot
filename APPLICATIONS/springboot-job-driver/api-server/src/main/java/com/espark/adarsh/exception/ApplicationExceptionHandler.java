package com.espark.adarsh.exception;


import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.entities.JobDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(JobExistException.class)
    public ResponseBean<JobDetail> jobExistException(JobExistException jobExistException){
        ResponseBean<JobDetail> responseBean = new ResponseBean<>();
        responseBean.setMessage(jobExistException.getMessage());
        return responseBean;
    }

    @ExceptionHandler(JobConfigurationNotFoundException.class)
    public ResponseBean<JobDetail> invalidJobAbortRequestException(JobConfigurationNotFoundException invalidJobAbortRequestException){
        ResponseBean<JobDetail> responseBean = new ResponseBean<>();
        responseBean.setMessage(invalidJobAbortRequestException.getMessage());
        return responseBean;
    }

    @ExceptionHandler(InvalidJobAbortRequestException.class)
    public ResponseBean<JobDetail> invalidJobAbortRequestException(InvalidJobAbortRequestException invalidJobAbortRequestException){
        ResponseBean<JobDetail> responseBean = new ResponseBean<>();
        responseBean.setMessage(invalidJobAbortRequestException.getMessage());
        return responseBean;
    }

    @ExceptionHandler(InvalidJobRequestException.class)
    public ResponseBean<JobDetail> invalidJobRequestException(InvalidJobRequestException invalidJobRequestException){
        ResponseBean<JobDetail> responseBean = new ResponseBean<>();
        responseBean.setMessage(invalidJobRequestException.getMessage());
        return responseBean;
    }

    @ExceptionHandler(Exception.class)
    public ResponseBean<JobDetail> exception(Exception exception){
        ResponseBean<JobDetail> responseBean = new ResponseBean<>();
        responseBean.setMessage(exception.getMessage());
        return responseBean;
    }
}
