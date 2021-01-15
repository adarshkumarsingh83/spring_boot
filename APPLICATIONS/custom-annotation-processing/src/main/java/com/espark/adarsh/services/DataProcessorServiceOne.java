package com.espark.adarsh.services;

import com.espark.adarsh.util.annotation.EsparkLowerCase;
import com.espark.adarsh.util.annotation.EsparkService;
import com.espark.adarsh.util.annotation.EsparkVar;
import org.springframework.stereotype.Service;

@Service
@EsparkService
public class DataProcessorServiceOne implements EsparkDataService {

    @EsparkVar(operation = "lower")
    private String dataProcessorServiceOneStore;

    @EsparkLowerCase(serviceId = "DataProcessorServiceOne")
    @Override
    public String getData(String inputData) {
        return inputData;
    }

}
