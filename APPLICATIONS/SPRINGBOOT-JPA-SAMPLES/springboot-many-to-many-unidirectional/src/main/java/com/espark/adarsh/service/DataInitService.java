package com.espark.adarsh.service;

import com.espark.adarsh.entity.Contact;
import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.datasource.init.DatabasePopulator;
import org.springframework.jdbc.datasource.init.DatabasePopulatorUtils;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Arrays;

@Service
public class DataInitService {


    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    DataSource dataSource;

    @PostConstruct
    public void init() {
        /*
        Resource initSchema = new ClassPathResource("data.sql");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);
        */

       /* employeeRepository.save(new Employee("adarsh", "kumar", "It",
                Arrays.asList(new Contact("bang street", "mp", "in"))));
        employeeRepository.save(new Employee("radha", "singh", "It",
                Arrays.asList(new Contact("hyd street", "ap", "in"))));
        employeeRepository.save(new Employee("sonu", "singh", "IT",
                Arrays.asList(new Contact("ald street", "up", "in"))));
        employeeRepository.save(new Employee("amit", "kumar", "Finance",
                Arrays.asList(new Contact("delhi street", "delhi", "in"))));*/
    }
}
