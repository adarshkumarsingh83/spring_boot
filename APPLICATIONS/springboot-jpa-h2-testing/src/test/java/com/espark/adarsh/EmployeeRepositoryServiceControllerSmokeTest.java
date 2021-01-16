package com.espark.adarsh;

import com.espark.adarsh.respository.EmployeeRepository;
import com.espark.adarsh.service.EmployeeService;
import com.espark.adarsh.web.EmployeeController;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
@SpringBootTest
public class EmployeeRepositoryServiceControllerSmokeTest {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private EmployeeService employeeService;

    @Autowired
    private EmployeeController employeeController;

    @Test
    void contextLoads() {
        Assertions.assertThat(this.employeeRepository).isNotNull();
        Assertions.assertThat(this.employeeService).isNotNull();
        Assertions.assertThat(this.employeeController).isNotNull();
    }
}
