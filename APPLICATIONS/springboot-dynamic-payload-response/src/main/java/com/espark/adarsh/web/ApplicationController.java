package com.espark.adarsh.web;

import com.espark.adarsh.bean.CustomResponseBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.service.ApiResponseService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ApplicationController {

    @Autowired
    ApiResponseService apiResponseService;

    @GetMapping("/api/response")
    ResponseBean getApiResponse(){
        return this.apiResponseService.getApiResponse();
    }

    @GetMapping("/api/custom/response")
    JsonNode getApiCustomResponse() throws JsonProcessingException {
        return this.apiResponseService.getApiCustomResponse();
    }
}
