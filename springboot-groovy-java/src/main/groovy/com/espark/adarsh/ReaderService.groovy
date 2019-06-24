package com.espark.adarsh

import groovy.json.JsonSlurper
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

import javax.annotation.PostConstruct

@Component
class ReaderService {

    @Autowired
    ApplicationContext context; ;

    @Autowired
    DataMaskingService dataMaskingService;

    def inputJSON = null; ;

    @PostConstruct
    public void init() {
        File inputFile = this.context.getResource('classpath:data/input.json').getFile();
        if (!inputFile.exists()) {
            throw new FileNotFoundException("input.json")
        }
        inputJSON = new JsonSlurper().parseFile(inputFile, 'UTF-8')
    }

    def readJsonData(){
        inputJSON = this.dataMaskingService.dataMaskingProcessor(inputJSON);
        return inputJSON;
    }

}
