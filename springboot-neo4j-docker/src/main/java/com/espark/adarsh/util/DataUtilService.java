package com.espark.adarsh.util;

import com.espark.adarsh.enitity.Department;
import com.espark.adarsh.enitity.Employee;
import com.espark.adarsh.enitity.Work;
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
        Department it = new Department();
        it.setName("IT DEPT");
        this.departmentRepository.save(it);

        Employee adarsh = new Employee();
        adarsh.setName("Adarsh kumar");
        this.employeeRepository.save(adarsh);

        Employee radha = new Employee();
        adarsh.setName("Radha Singh");
        this.employeeRepository.save(radha);

        Work workAsManager = new Work();
        workAsManager.setEmployee(adarsh);
        workAsManager.setDepartment(it);
        workAsManager.setWork(Arrays.asList("manager"));
        it.getWorks().add(workAsManager);

        Work workAsLead = new Work();
        workAsLead.setEmployee(adarsh);
        workAsLead.setDepartment(it);
        workAsLead.setWork(Arrays.asList("Lead"));
        it.getWorks().add(workAsLead);

        this.departmentRepository.save(it);
    }

}
