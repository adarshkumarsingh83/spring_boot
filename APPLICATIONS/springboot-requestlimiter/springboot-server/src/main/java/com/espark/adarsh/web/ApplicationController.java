package com.espark.adarsh.web;

import com.espark.adarsh.record.Employee;
import com.espark.adarsh.bean.RequestBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.service.ApplicationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;


@RestController
public class ApplicationController {

    Logger log = LoggerFactory.getLogger(ApplicationController.class);

    ApplicationService applicationService;

    public ApplicationController(ApplicationService applicationService) {
        this.applicationService = applicationService;
    }

    @PostMapping("/employee")
    public ResponseEntity<ResponseBean<Employee>> save(@RequestHeader Map<String,String> headers,
            @RequestBody RequestBean<Employee> requestBean) {
        log.info("ApplicationController::save currentThread{} employeeId {}",
               Thread.currentThread().getName(), requestBean.getData().id());
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("id",headers.get("id"));
        ResponseBean responseBean = this.applicationService.saveEmployee.apply(requestBean);
        responseBean.setBatchId(requestBean.getBatchId());
        log.info("ApplicationController::save currentThread{} employeeId {}",
                Thread.currentThread().getName(), requestBean.getData().id());
        return new ResponseEntity<>(responseBean,httpHeaders, HttpStatus.OK);
    }
}
