package com.espark.adarsh.controller;

import com.espark.adarsh.validator.SchemaValidationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RestService {

    @Autowired
    private SchemaValidationService schemaValidationService;

    @RequestMapping(method = RequestMethod.POST,value = "/espark/data")
    public String saveData(@RequestBody String data){
        try {
            this.schemaValidationService.validateRequest(data);
            return data.toUpperCase();
        }catch (Exception e){
            return e.getMessage();
        }
    }
}
