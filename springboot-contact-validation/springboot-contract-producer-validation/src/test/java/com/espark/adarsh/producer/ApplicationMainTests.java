package com.espark.adarsh.producer;

import java.util.Optional;

import com.espark.adarsh.producer.bean.Employee;
import com.espark.adarsh.producer.ApplicationMain;
import com.espark.adarsh.producer.service.EmployeeService;
import com.espark.adarsh.producer.web.EmployeeController;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@SpringBootTest(classes = ApplicationMain.class)
@RunWith(SpringRunner.class)
class ApplicationMainTests {

	@Autowired
	EmployeeController employeeController;


	@MockBean
	private EmployeeService employeeService;

	@Before
	public void before() {
		final Employee aEmployee = new Employee(1, "Adarsh", "Kumar", 1000.0, "M");
		Mockito.when(this.employeeService.findById(1)).thenReturn(Optional.of(aEmployee));
		RestAssuredMockMvc.standaloneSetup(this.employeeController);
	}

}
