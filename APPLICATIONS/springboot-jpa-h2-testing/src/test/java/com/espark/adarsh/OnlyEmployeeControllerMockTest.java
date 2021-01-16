package com.espark.adarsh;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import com.espark.adarsh.web.EmployeeController;
import lombok.extern.slf4j.Slf4j;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.LinkedList;
import java.util.List;

import static org.mockito.Mockito.times;

/**
 * load the whole context but without server start
 */
@Slf4j
@WebMvcTest(EmployeeController.class)
public class OnlyEmployeeControllerMockTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private EmployeeService employeeService;

    @Test
    public void getAllEmployeeTest() throws Exception {
        List<Employee> employeeList = new LinkedList<>();
        Employee employee = new Employee();
        employee.setId(1L);
        employee.setFirstName("xxx");
        employee.setLastName("kkk");
        employee.setCareer("oooo");
        employeeList.add(employee);
        Mockito.when(employeeService.getAllEmployee()).thenReturn(employeeList);
        this.mockMvc.perform(MockMvcRequestBuilders.get("/api/employees"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(Matchers.containsString("ooo")));
        Mockito.verify(employeeService, times(1)).getAllEmployee();
    }
}
