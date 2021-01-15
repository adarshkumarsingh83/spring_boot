package com.espark.adarsh.service;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.bean.ProjectsBean;
import com.espark.adarsh.respository.ProjectsRepository;
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
    ProjectsRepository projectsRepository;

    public List<EmployeeBean> getEmployee() {
        List<EmployeeBean> employeeBeans = new LinkedList<>();
        this.employeeRepository
                .findAll()
                .forEach(employee -> employeeBeans.add(new EmployeeBean(employee,
                        employee.getProjects()
                                .stream()
                                .map(employeeExperience ->
                                        new ProjectsBean(employeeExperience)
                                ).collect(Collectors.toList()))));
        return employeeBeans;
    }

    public List<ProjectsBean> getEmployeeExperience() {
        List<ProjectsBean> projectsBeans = new LinkedList<>();
        this.projectsRepository
                .findAll()
                .forEach(projects ->
                        projectsBeans.add(new ProjectsBean(projects
                                , projects.getEmployees()
                                .stream()
                                .map(employee -> new EmployeeBean(employee))
                                .collect(Collectors.toList())))
                );
        return projectsBeans;
    }

}
