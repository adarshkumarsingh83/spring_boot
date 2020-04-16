package com.espark.adarsh;

import com.espark.adarsh.persistance.entity.Employee;
import com.espark.adarsh.persistance.repository.EmployeeRepository;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EmployeeRepository repository;

    @Test
    public void testFindByName() {

        entityManager.persist(new Employee("C++"));
        List<Employee> books = repository.findByName("C++");
        assertEquals(1, books.size());
        Assertions.assertThat(books).extracting(Employee::getName).containsOnly("C++");

    }

}
