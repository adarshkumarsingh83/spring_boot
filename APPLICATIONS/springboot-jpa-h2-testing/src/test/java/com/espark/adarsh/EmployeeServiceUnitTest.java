package com.espark.adarsh;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.respository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import java.util.LinkedList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
class EmployeeServiceUnitTest {

	@Autowired
	EmployeeRepository employeeRepository;


	@Test
	public void getAllEmployee(){
		List<Employee> employeeList = new LinkedList<>();
		this.employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
		assertNotNull(employeeList.get(0));
		assertNotNull(employeeList.get(1));
		assertNotNull(employeeList.get(2));
		assertNotNull(employeeList.get(3));
	}
}
