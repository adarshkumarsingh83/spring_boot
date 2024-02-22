package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Calendar;

@Service
public class DbInitService {

    @Autowired
    EmployeeRepository employeeRepository;

    @PostConstruct
    public void init(){

        Calendar adarshDob = Calendar.getInstance();
        adarshDob.set(2020, 1, 1);
        Employee adarsh = new Employee("Adarsh"
                , "Singh"
                , "adarsh@espark"
                , adarshDob.getTime());
        adarsh = employeeRepository.save(adarsh);

        Calendar radhaDob = Calendar.getInstance();
        radhaDob.set(2020, 1, 1);
        Employee radha = new Employee("Radha"
                , "Singh"
                , "radha@espark"
                , radhaDob.getTime());
        radha = employeeRepository.save(radha);
    }

}
