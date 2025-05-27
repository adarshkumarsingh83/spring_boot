package com.espark.adarsh.exception.handler;

import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.exception.EmployeeAlreadyExistsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EmployeeAlreadyExistsException.class)
    public ResponseBean<String> employeeAlreadyExists(EmployeeAlreadyExistsException employeeAlreadyExists){
        ResponseBean<String> responseBean = new ResponseBean<>();
        responseBean.setDate(employeeAlreadyExists.getMessage());
        responseBean.setMessage("Employee Already Exists");
        return  responseBean;
    }

    @ExceptionHandler(Exception.class)
    public ResponseBean<String> exception(Exception exception){
        ResponseBean<String> responseBean = new ResponseBean<>();
        responseBean.setDate(exception.getMessage());
        responseBean.setMessage("Exception Generated ");
        return  responseBean;
    }
}
