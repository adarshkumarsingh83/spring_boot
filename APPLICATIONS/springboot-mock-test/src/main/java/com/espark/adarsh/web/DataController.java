package com.espark.adarsh.web;

import com.espark.adarsh.service.DataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DataController {

    @Autowired
    private DataService dataService;

    @RequestMapping(value = "/data/{range}", method = RequestMethod.GET)
    public List<Integer> retrieveAllData(@PathVariable("range") int range) {
        return this.dataService.retrieveAllData(range);
    }

    @RequestMapping(value = "/even/{range}", method = RequestMethod.GET)
    public List<Integer> retrieveEvenData(@PathVariable("range") int range) {
        return this.dataService.findEvenNumber(range);
    }

    @RequestMapping(value = "/odd/{range}", method = RequestMethod.GET)
    public List<Integer> retrieveOddData(@PathVariable("range") int range) {
        return this.dataService.findOddNumber(range);
    }
}
