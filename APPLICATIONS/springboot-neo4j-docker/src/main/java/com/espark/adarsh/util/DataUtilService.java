package com.espark.adarsh.util;

import com.espark.adarsh.enitity.Department;
import com.espark.adarsh.enitity.Employee;
import com.espark.adarsh.repository.DepartmentRepository;
import com.espark.adarsh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Arrays;

@Service
public class DataUtilService {

    @Autowired
    DepartmentRepository departmentRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @PostConstruct
    public void init() {

        Employee adarsh = new Employee();
        adarsh.setName("Adarsh kumar");
        adarsh = this.employeeRepository.save(adarsh);

        Employee radha = new Employee();
        radha.setName("Radha Singh");
        radha = this.employeeRepository.save(radha);

        Department itdept = new Department();
        itdept.setName("IT DEPT");
        itdept.getWorks().add(radha);
        itdept.getWorks().add(adarsh);
        this.departmentRepository.save(itdept);


        Employee amit = new Employee();
        amit.setName("Amit Kumar");
        amit = this.employeeRepository.save(amit);

        Employee geeta = new Employee();
        geeta.setName("Geeta Singh");
        geeta = this.employeeRepository.save(geeta);

        Department accountDept = new Department();
        accountDept.setName("ACCOUNT DEPT");
        accountDept.getWorks().add(amit);
        accountDept.getWorks().add(geeta);
        this.departmentRepository.save(accountDept);
    }

}
