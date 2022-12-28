package com.espark.adarsh.exception;


import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler {

    @ExceptionHandler(EmployeeNotFoundException.class)
    ProblemDetail employeeNotFoundException(EmployeeNotFoundException e) {
        var pd = ProblemDetail.forStatusAndDetail(HttpStatusCode.valueOf(404), e.getMessage());
        pd.setTitle("employee not found");
        return pd;
    }
}
