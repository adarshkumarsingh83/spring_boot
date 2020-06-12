package com.espark.adarsh.web;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.bean.EmployeeExperienceBean;
import com.espark.adarsh.service.EmployeeInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    EmployeeInfoService employeeInfoService;

    @GetMapping("/employee")
    public List<EmployeeBean> getEmployees() {
        return this.employeeInfoService.getEmployee();
    }

    @GetMapping("/employee-experience")
    public List<EmployeeExperienceBean> getEmployeeExperience() {
        return this.employeeInfoService.getEmployeeExperience();
    }
}
