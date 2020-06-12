/*
********************************************************
        *         Copyright (c) 2016 Cisco     *
        *         All rights reserved.         *
********************************************************
*/
package com.espark.adarsh.handler;


import com.espark.adarsh.bean.ResponseBean;
import org.hibernate.service.spi.ServiceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.ResourceAccessException;


/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 *
 * This class provide the implementation for global exception
 * handler for the whole application which handle the
 * MozaiqWSResponseErrorException as weel as the Exception also.
 */
@ControllerAdvice
public class ApiExceptionHandler {


    private static final Logger LOGGER= LoggerFactory.getLogger(ApiExceptionHandler.class);


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public
    @ResponseBody
    ResponseBean handleCustomException(MethodArgumentNotValidException ex) {
        LOGGER.error("INVALID INPUT EXCEPTION HANDLER ",ex);
        int count = 0;
        int errorCount = ex.getBindingResult().getAllErrors().size();
        StringBuilder sb = new StringBuilder("");
        for (ObjectError error : ex.getBindingResult().getAllErrors()) {
            count++;
            if (error instanceof FieldError) {
                FieldError fieldError = (FieldError)error;
                sb.append(fieldError.getField() + " : " + fieldError.getDefaultMessage());
            } else {
                sb.append(error.getDefaultMessage());
            }
            if (count < errorCount)
                sb.append(", ");
        }

        if (null != sb.toString() && sb.toString().endsWith(", "))
            sb.delete(sb.length() -2 , sb.length());
        LOGGER.error("Invalid input(s). " + sb.toString());
        final ResponseBean responseBase = new ResponseBean();
        responseBase.setStatusCode(403);
        responseBase.setStatusMessage("Invalid input(s). " + sb.toString());
        responseBase.setSuccess(false);
        return responseBase;
    }



    @ExceptionHandler({NumberFormatException.class, TypeMismatchException.class})
    public
    @ResponseBody
    ResponseBean handleCustomException(Exception ex) {
        LOGGER.error("NUMBER FORMAT EXCEPTION HANDLER ",ex);
        final ResponseBean responseBase = new ResponseBean();
        responseBase.setStatusCode(403);
        responseBase.setStatusMessage("INVALID_INPUT");
        responseBase.setSuccess(false);
        return responseBase;
    }

    //

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public
    @ResponseBody
    ResponseBean handleCustomException(HttpMessageNotReadableException ex) {
        LOGGER.error("Unable to read HTTP request body.",ex);
        final ResponseBean responseBase = new ResponseBean();
        responseBase.setStatusCode(403);
        responseBase.setStatusMessage("Unable to read request content.");
        responseBase.setSuccess(false);
        return responseBase;
    }

    @ExceptionHandler(HttpMediaTypeNotAcceptableException.class)
    public
    @ResponseBody
    ResponseBean handleCustomException(HttpMediaTypeNotAcceptableException ex) {
        LOGGER.error("MEDIA TYPE NOT ACCEPTABLE ",ex);
        final ResponseBean responseBase = new ResponseBean();
        responseBase.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseBase.setStatusMessage("MEDIA_TYPE_NOT_ACCEPTABLE");
        responseBase.setSuccess(false);
        return responseBase;
    }

    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public
    @ResponseBody
    ResponseBean handleCustomException(HttpMediaTypeNotSupportedException ex) {
        LOGGER.error("Unable to read HTTP request body. Media type not supported. ",ex);
        final ResponseBean responseBase = new ResponseBean();
        responseBase.setStatusCode(403);
        responseBase.setStatusMessage("Unable to read request content. Media type not supported.");
        responseBase.setSuccess(false);
        return responseBase;
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public
    @ResponseBody
    ResponseBean handleCustomException(HttpRequestMethodNotSupportedException ex) {
        LOGGER.error("No request handler found for the request HTTP method : "
                + ((HttpRequestMethodNotSupportedException) ex).getMethod());
        final ResponseBean responseBase = new ResponseBean();
        responseBase.setStatusCode(400);
        responseBase.setStatusMessage("NOT_IMPLEMENTED");
        responseBase.setSuccess(false);
        return responseBase;
    }

    @ExceptionHandler(ServiceException.class)
    public
    @ResponseBody
    ResponseBean handleCustomException(ServiceException ex) {
        LOGGER.error("SERVICE EXCEPTION HANDLER ",ex);
        final ResponseBean responseBase = new ResponseBean();
        responseBase.setStatusCode(400);
        responseBase.setStatusMessage(ex.getLocalizedMessage());
        responseBase.setSuccess(false);
        return responseBase;
    }



    @ExceptionHandler(ResourceAccessException.class)
    public
    @ResponseBody
    ResponseBean handleCustomException(ResourceAccessException ex) {
        LOGGER.error("EXTERNAL SYSTEM CONNECTION EXCEPTION HANDLER ",ex);
        final ResponseBean responseBase = new ResponseBean();
        responseBase.setStatusCode(500);
        responseBase.setStatusMessage("CLIENT_CONNECTION_REFUSED");
        responseBase.setSuccess(false);
        return responseBase;
    }

    @ExceptionHandler(Exception.class)
    public
    @ResponseBody
    ResponseBean handleAllException(Exception ex) {
        LOGGER.error("DEFAULT EXCEPTION HANDLER ",ex);
        final ResponseBean responseBase = new ResponseBean();
        responseBase.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
        responseBase.setStatusMessage(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
        responseBase.setSuccess(false);
        return responseBase;
    }


}
