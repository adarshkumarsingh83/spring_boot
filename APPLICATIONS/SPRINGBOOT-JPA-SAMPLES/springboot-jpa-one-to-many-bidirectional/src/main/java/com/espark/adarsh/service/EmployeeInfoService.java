package com.espark.adarsh.service;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.bean.EmployeeExperienceBean;
import com.espark.adarsh.respository.EmployeeExperienceRepository;
import com.espark.adarsh.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeInfoService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeExperienceRepository employeeExperienceRepository;

    public List<EmployeeBean> getEmployee() {
        List<EmployeeBean> employeeBeans = new LinkedList<>();
        this.employeeRepository
                .findAll()
                .forEach(employee -> employeeBeans.add(new EmployeeBean(employee,
                        employee.getEmployeeExperience()
                                .stream()
                                .map(employeeExperience ->
                                        new EmployeeExperienceBean(employeeExperience)
                                ).collect(Collectors.toList()))));
        return employeeBeans;
    }

    public List<EmployeeExperienceBean> getEmployeeExperience() {
        List<EmployeeExperienceBean> employeeExperienceBeans = new LinkedList<>();
        this.employeeExperienceRepository
                .findAll()
                .forEach(employeeExperience ->
                        employeeExperienceBeans.add(new EmployeeExperienceBean(employeeExperience
                                , new EmployeeBean(employeeExperience.getEmployee())))
                );
        return employeeExperienceBeans;
    }

}
