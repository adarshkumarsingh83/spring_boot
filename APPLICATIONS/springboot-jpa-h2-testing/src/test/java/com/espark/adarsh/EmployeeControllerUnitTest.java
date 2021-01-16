package com.espark.adarsh;

import com.espark.adarsh.entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import java.util.Arrays;
import java.util.List;


@Slf4j
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeControllerUnitTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void getAllEmployeeTest() throws Exception {
        Employee[] employeeArray = this.restTemplate.getForObject("http://localhost:" + port + "/api/employees", Employee[].class);
        List<Employee> employeeList = Arrays.asList(employeeArray);
        Assertions.assertThat(employeeList.get(0).getId()).isEqualTo(1);
        Assertions.assertThat(employeeList.get(1).getId()).isEqualTo(2);
        Assertions.assertThat(employeeList.get(2).getId()).isEqualTo(3);
        Assertions.assertThat(employeeList.get(3).getId()).isEqualTo(4);
    }
}
