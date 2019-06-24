package com.espark.adarsh.exception;


import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class DefaultApplicationExceptionHandler{

    @ExceptionHandler({ Exception.class })
    public @ResponseBody ApplicationException handleException(Exception ex, WebRequest request) {
      return new ApplicationException(ex);
    }

}
