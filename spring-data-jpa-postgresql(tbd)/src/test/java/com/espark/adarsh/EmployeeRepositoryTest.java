package com.espark.adarsh;

import com.espark.adarsh.persistance.entity.Employee;
import com.espark.adarsh.persistance.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = ApplicationMain.class)
public class EmployeeRepositoryTest {


   /* @Autowired
    private EmployeeRepository repository;

    @Test
    public void testFindByName() {
        repository.save(new Employee("C++"));
        List<Employee> books = repository.findByName("C++");
        assertEquals(1, books.size());
        Assertions.assertThat(books).extracting(Employee::getName).containsOnly("C++");
    }*/

}
