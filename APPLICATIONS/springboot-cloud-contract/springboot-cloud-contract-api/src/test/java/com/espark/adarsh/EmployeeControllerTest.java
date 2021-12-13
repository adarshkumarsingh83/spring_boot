package com.espark.adarsh;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.repository.EmployeeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;

@AutoConfigureMockMvc
@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
public class EmployeeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    EmployeeRepository employeeRepository;

    @Test
    public void shouldReturnAllEmployees() throws Exception {

        Mockito.when(this.employeeRepository.findAll())
                .thenReturn(Arrays.asList(new Employee(1L, "Adarsh")
                        , new Employee(2L, "Radha")));

        this.mockMvc.perform(MockMvcRequestBuilders.get("/employees"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].id").value(1L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[0].name").value("Adarsh"))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].id").value(2L))
                .andExpect(MockMvcResultMatchers.jsonPath("@.[1].name").value("Radha"))
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
