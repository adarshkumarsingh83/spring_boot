package com.espark.adarsh.services;

import com.espark.adarsh.annotation.EsparkService;
import com.espark.adarsh.annotation.EsparkUpperCase;
import com.espark.adarsh.annotation.EsparkVar;
import org.springframework.stereotype.Service;

@Service
@EsparkService
public class DataProcessorServiceTwo implements EsparkDataService {

    @EsparkVar(operation = "upper")
    private String dataProcessorServiceTwoStore;

    @EsparkUpperCase(serviceId = "DataProcessorServiceTwo")
    @Override
    public String getData(String inputData) {
        return inputData;
    }
}
