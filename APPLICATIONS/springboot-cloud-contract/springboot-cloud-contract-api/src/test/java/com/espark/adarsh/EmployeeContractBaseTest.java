package com.espark.adarsh;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.repository.EmployeeRepository;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;

@SpringBootTest(classes = SpringbootApplicationMain.class)
@ExtendWith(SpringExtension.class)
public class EmployeeContractBaseTest {

    @Autowired
    EmployeeController employeeController;

    @MockBean
    EmployeeRepository employeeRepository;

    @BeforeEach
    public void init(){
        Mockito.when(this.employeeRepository.findAll())
                .thenReturn(Arrays.asList(new Employee(1L, "Adarsh")
                        , new Employee(2L, "Radha")));
        RestAssuredMockMvc.standaloneSetup(this.employeeController);
    }
}
