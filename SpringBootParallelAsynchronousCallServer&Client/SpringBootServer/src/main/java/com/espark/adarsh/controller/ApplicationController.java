package com.espark.adarsh.controller;


import com.espark.adarsh.bean.ApplicationBean;
import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ApplicationController {

    private final Logger logger = LoggerFactory.getLogger(ApplicationController.class);

    @Value("${message}")
    private String message;

    @Qualifier(value = "employeeService")
    @Autowired(required = true)
    private EmployeeService employeeService;

    @RequestMapping(value = "/application/welcome"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public
    @ResponseBody
    Map<String, String> welcomeMessage() {
        final Map<String, String> msg = new HashMap<String, String>() {
            {
                put("name", System.getProperty("user.name"));
                put("msg", "Hello " + System.getProperty("user.name") + " " + message);
            }
        };
        logger.info("Request Received in Application Controller ");
        return msg;
    }

    @RequestMapping(value = "/application/save"
            , consumes = {"application/json"}
            , method = RequestMethod.POST)
    public
    @ResponseBody
    ApplicationBean<String> saveEmployee(@RequestBody Employee employee) {
        return new ApplicationBean<String>((this.employeeService.saveEmployee(employee) ? "Employee Saved" : "Employee Not Saved "), HttpStatus.OK.toString());
    }


    @RequestMapping(value = "/application/employee"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public
    @ResponseBody
    ApplicationBean<List<Employee>> getEmployee() {
        return new ApplicationBean<List<Employee>>(this.employeeService.getEmployees(), HttpStatus.OK.toString());
    }

    @RequestMapping(value = "/application/employee/{empId}"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public
    @ResponseBody
    ApplicationBean<Employee> getEmployee(@PathVariable String empId) {
        return new ApplicationBean<Employee>(this.employeeService.getEmployee(empId), HttpStatus.OK.toString());
    }

    @RequestMapping(value = "/application/employee/{empId}"
            , method = RequestMethod.DELETE)
    public
    @ResponseBody
    ApplicationBean<String> deleteEmployee(@PathVariable String empId) {
        return new ApplicationBean<String>((this.employeeService.deleteEmployee(empId) ? "Employee Deleted" : "Employee Not Deleted "), HttpStatus.OK.toString());
    }

    @RequestMapping(value = "/application/update"
            , consumes = {"application/json"}
            , method = RequestMethod.PUT)
    public
    @ResponseBody
    ApplicationBean<String> updateEmployee(@RequestBody Employee employee) {
        return new ApplicationBean<String>((this.employeeService.updateEmployee(employee) ? "Employee Updated" : "Employee Not Updated "), HttpStatus.OK.toString());
    }

}
