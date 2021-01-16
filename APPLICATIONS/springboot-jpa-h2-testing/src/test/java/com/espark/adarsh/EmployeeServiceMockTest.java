package com.espark.adarsh;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.respository.EmployeeRepository;
import com.espark.adarsh.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.MockBeans;
import java.util.LinkedList;
import java.util.List;
import static org.mockito.Mockito.times;

@Slf4j
@SpringBootTest
@MockBeans({@MockBean(EmployeeService.class), @MockBean(EmployeeRepository.class)})
class EmployeeServiceMockTest {

    @Autowired
    private EmployeeService employeeService;

    @Test
    public void getAllEmployeeTest() {
        List<Employee> employeeList = new LinkedList<>();
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("xxx");
        employee.setLastName("kkk");
        employee.setCareer("oooo");
        employeeList.add(employee);
        Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeList);
        List<Employee> employees = employeeService.getAllEmployee();
        Mockito.verify(employeeService, times(1)).getAllEmployee();
        log.info("==> {}", employees);
    }
}
