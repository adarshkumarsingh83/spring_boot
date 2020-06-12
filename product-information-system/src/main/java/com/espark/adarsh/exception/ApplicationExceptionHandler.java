package com.espark.adarsh.exception;

import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.bean.TreeNodeBean;
import com.espark.adarsh.util.MessageUtil;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Slf4j
@ControllerAdvice
public class ApplicationExceptionHandler
        extends ResponseEntityExceptionHandler {


    @Autowired
    private MessageUtil messageUtil;

    @ExceptionHandler(value
            = {Exception.class})
    protected ResponseBean<Exception, String> handleApplicationException(
            Exception ex, WebRequest request) {
        log.error("label=exception-handler exception={}", ex);
        return new ResponseBean(null, ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value
            = {IllegalArgumentException.class, IllegalStateException.class})
    protected ResponseBean<Exception, String> handleConflict(
            RuntimeException ex, WebRequest request) {
        log.error("label=exception-handler exception={}", ex);
        return new ResponseBean(null, ex.getMessage(), HttpStatus.SERVICE_UNAVAILABLE, ex.getLocalizedMessage());
    }

    @ExceptionHandler(value
            = {ResourceNotFound.class})
    protected ResponseBean<ResourceNotFound, String> handleResourceNotFound(ResourceNotFound resourceNotFound) {
        log.error("label=exception-handler exception={}", resourceNotFound);
        return new ResponseBean(null, resourceNotFound.getErrorCode(), HttpStatus.NOT_FOUND,
                messageUtil.get(resourceNotFound.getErrorCode(), resourceNotFound.getParamList()));
    }

    @ExceptionHandler(value
            = {ValidationFailed.class})
    protected ResponseBean<ValidationFailed, String> handleValidationFailed(ValidationFailed validationFailed) {
        log.error("label=exception-handler exception={}", validationFailed);
        return new ResponseBean(null, validationFailed.getErrorCode(), HttpStatus.BAD_REQUEST,
                messageUtil.get(validationFailed.getErrorCode(), validationFailed.getParamList()));
    }

    @ExceptionHandler(value
            = {ApplicationException.class})
    protected ResponseBean<ValidationFailed, String> handleApplicationException(ApplicationException applicationException) {
        log.error("label=exception-handler exception={}", applicationException);
        return new ResponseBean(null, applicationException.getErrorCode(), HttpStatus.BAD_REQUEST,
                applicationException.getLocalizedMessage());
    }
}
