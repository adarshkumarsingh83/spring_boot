package com.espark.adarsh.producer;

import static io.restassured.RestAssured.get;
import static org.hamcrest.CoreMatchers.equalTo;

import javax.annotation.PostConstruct;

import com.espark.adarsh.producer.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class EmployeeControllerTest extends ApplicationMainTests {

    @LocalServerPort
    private int        port;

    private String     uri;

    @Autowired
    EmployeeService employeeRepository;

    @PostConstruct
    public void init() {
        uri = "http://localhost:" + port;
    }


    @Test
    public void getEmployeeFoundTest() {
        get(uri + "/employee/1").then().assertThat().statusCode(200).body("fname", equalTo("Adarsh"));
    }

    @Test
    public void getEmployeeNotFoundTest() {
        get(uri + "/employee/21").then().assertThat().statusCode(404);
    }

}
