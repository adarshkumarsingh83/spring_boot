package com.espark.adarsh.service;

import com.espark.adarsh.entities.Gender;
import com.espark.adarsh.entities.Employee;
import com.espark.adarsh.entities.EmployeeExperience;
import com.espark.adarsh.respository.EmployeeExperienceRepository;
import com.espark.adarsh.respository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Arrays;
import java.util.Calendar;

@Service
public class DbInitService {


    @Autowired
    DataSource dataSource;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EmployeeExperienceRepository employeeExperienceRepository;

    @PostConstruct
    public void init() {
        Calendar adarshDob = Calendar.getInstance();
        adarshDob.set(2020, 1, 1);
        Employee adarsh = new Employee("Adarsh"
                , "Singh"
                , Gender.MALE
                , "adarsh@espark"
                , adarshDob.getTime());

        Calendar adarshCoy1Start = Calendar.getInstance();
        adarshCoy1Start.set(2001, 1, 1);
        Calendar adarshCoy1End = Calendar.getInstance();
        adarshCoy1End.set(2010, 1, 1);
        EmployeeExperience adarshCoy1 = new EmployeeExperience(
                "coy1"
                ,"+1-1122334455"
                , adarshCoy1Start.getTime()
                , adarshCoy1End.getTime()
                ,""
                , "Indian Point"
                , "Indian Street"
                , "Dallas"
                , "TX"
                , "US"
                , "347571");


        Calendar adarshCoy2Start = Calendar.getInstance();
        adarshCoy2Start.set(2010, 1, 1);
        Calendar adarshCoy2End = Calendar.getInstance();
        adarshCoy2End.set(2020, 1, 1);
        EmployeeExperience adarshCoy2 = new EmployeeExperience(
                "coy2"
                ,"+1-1122334455"
                , adarshCoy2Start.getTime()
                , adarshCoy2End.getTime()
                ,""
                , "Indian Point"
                , "Indian Street"
                , "Dallas"
                , "TX"
                , "US"
                , "347571");

        adarsh.setEmployeeExperience(Arrays.asList(adarshCoy1,adarshCoy2));
        adarshCoy1.setEmployee(adarsh);
        adarshCoy2.setEmployee(adarsh);
        employeeRepository.save(adarsh);


        Calendar radhaDob = Calendar.getInstance();
        radhaDob.set(2020, 1, 1);
        Employee radha = new Employee("Radha"
                , "Singh"
                , Gender.MALE
                , "radha@espark"
                , radhaDob.getTime());

        Calendar radhaCoy1Start = Calendar.getInstance();
        radhaCoy1Start.set(2001, 1, 1);
        Calendar radhaCoy1End = Calendar.getInstance();
        radhaCoy1End.set(2010, 1, 1);
        EmployeeExperience radhaCoy1 = new EmployeeExperience(
                "coy1"
                ,"+1-55226688"
                , radhaCoy1Start.getTime()
                , radhaCoy1End.getTime()
                ,""
                , "Indian Point"
                , "Indian Street"
                , "Dallas"
                , "TX"
                , "US"
                , "347571");


        Calendar radhaCoy2Start = Calendar.getInstance();
        radhaCoy2Start.set(2010, 1, 1);
        Calendar radhaCoy2End = Calendar.getInstance();
        radhaCoy2End.set(2020, 1, 1);
        EmployeeExperience radhaCoy2 = new EmployeeExperience(
                "coy2"
                ,"+1-1122334455"
                , radhaCoy2Start.getTime()
                , radhaCoy2End.getTime()
                ,""
                , "Indian Point"
                , "Indian Street"
                , "Dallas"
                , "TX"
                , "US"
                , "347571");

        radha.setEmployeeExperience(Arrays.asList(radhaCoy1,radhaCoy2));
        radhaCoy1.setEmployee(radha);
        radhaCoy2.setEmployee(radha);
        employeeRepository.save(radha);

       /* Resource initSchema = new ClassPathResource("db.sql_bkp");
        DatabasePopulator databasePopulator = new ResourceDatabasePopulator(initSchema);
        DatabasePopulatorUtils.execute(databasePopulator, dataSource);*/
    }

}
