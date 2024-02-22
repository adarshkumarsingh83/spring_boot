package com.espark.adarsh.service;

import com.espark.adarsh.entities.Gender;
import com.espark.adarsh.entities.Employee;
import com.espark.adarsh.entities.Projects;
import com.espark.adarsh.respository.ProjectsRepository;
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
    ProjectsRepository projectsRepository;

    @PostConstruct
    public void init() {

        Calendar proj1Start = Calendar.getInstance();
        proj1Start.set(2001, 1, 1);
        Calendar proj1End = Calendar.getInstance();
        proj1End.set(2010, 1, 1);
        Projects projectsOne = new Projects(
                "project1"
                , "+1-1122334455"
                , proj1Start.getTime()
                , proj1End.getTime()
                , ""
                , "Indian Point"
                , "Indian Street"
                , "Dallas"
                , "TX"
                , "US"
                , "347571");


        Calendar proj2Start = Calendar.getInstance();
        proj2Start.set(2010, 1, 1);
        Calendar proj2End = Calendar.getInstance();
        proj2End.set(2020, 1, 1);
        Projects projectsTwo = new Projects(
                "project2"
                , "+1-1122334455"
                , proj2Start.getTime()
                , proj2End.getTime()
                , ""
                , "Indian Point"
                , "Indian Street"
                , "Dallas"
                , "TX"
                , "US"
                , "347571");

        projectsOne = this.projectsRepository.save(projectsOne);
        projectsTwo = this.projectsRepository.save(projectsTwo);

        Calendar adarshDob = Calendar.getInstance();
        adarshDob.set(2020, 1, 1);
        Employee adarsh = new Employee("Adarsh"
                , "Singh"
                , Gender.MALE
                , "adarsh@espark"
                , adarshDob.getTime());
        adarsh = employeeRepository.save(adarsh);


        Calendar radhaDob = Calendar.getInstance();
        radhaDob.set(2020, 1, 1);
        Employee radha = new Employee("Radha"
                , "Singh"
                , Gender.MALE
                , "radha@espark"
                , radhaDob.getTime());
        radha = employeeRepository.save(radha);

        projectsOne.setEmployee(adarsh);
        projectsOne.setEmployee(radha);
        this.projectsRepository.save(projectsOne);

        projectsTwo.setEmployee(adarsh);
        projectsTwo.setEmployee(radha);
        this.projectsRepository.save(projectsTwo);

        adarsh.setProject(projectsOne);
        adarsh.setProject(projectsTwo);
        this.employeeRepository.save(adarsh);
        radha.setProject(projectsOne);
        radha.setProject(projectsTwo);
        this.employeeRepository.save(radha);


    }

}
