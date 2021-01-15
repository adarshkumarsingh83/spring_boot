package com.espark.adarsh.web;

import com.espark.adarsh.util.processor.AnnotationProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApplicationController {


    @Autowired
    private AnnotationProcessor annotationProcessor;

    @RequestMapping(value = "/listmethod", method = RequestMethod.GET)
    public Map<String, List<String>> getMethodInformation() {
        Map<String, List<String>> map = new HashMap<>();
        map.put("upper", this.annotationProcessor.getUpperEsparkService());
        map.put("lower", this.annotationProcessor.getLowerEsparkService());
        return map;
    }

    @RequestMapping(value = "/listfield", method = RequestMethod.GET)
    public Map<String, String> getFieldInformation() {
        Map<String, String> map = this.annotationProcessor.getMap();
        return map;
    }
}
