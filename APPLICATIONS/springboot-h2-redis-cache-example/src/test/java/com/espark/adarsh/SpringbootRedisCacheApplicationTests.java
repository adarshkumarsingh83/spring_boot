package com.espark.adarsh;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;
import org.testcontainers.shaded.org.hamcrest.CoreMatchers;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Testcontainers
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@SpringBootTest(classes = ApplicationMain.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootRedisCacheApplicationTests {


    @Container
    public static GenericContainer container = new GenericContainer("redis:latest")
            .withExposedPorts(6379);

    @DynamicPropertySource
    static void properties(DynamicPropertyRegistry registry) {
        registry.add("spring.redis.host", container::getContainerIpAddress);
        registry.add("spring.redis.port", container::getFirstMappedPort);
    }

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void testGetAllEmployee() throws Exception {
        Employee employeeAdmin = new Employee(10l, "admin", "admin", "admin");
        Employee employeeUser = new Employee(20l, "user", "user", "user");
        employeeService.saveEmployee(employeeAdmin);
        employeeService.saveEmployee(employeeUser);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employees")
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.[*].id").isNotEmpty());
    }

    @Test
    void testGetEmployee() throws Exception {
        Employee employeeAdmin = new Employee(10l, "admin", "admin", "admin");
        Employee employeeUser = new Employee(20l, "user", "user", "user");
        employeeService.saveEmployee(employeeAdmin);
        employeeService.saveEmployee(employeeUser);

        mockMvc.perform(MockMvcRequestBuilders
                        .get("/employee/{id}", 1)
                        .accept(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.*").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

    @Test
    void testSaveEmployee() throws Exception {
        Employee employeeAdmin = new Employee(10l, "admin", "admin", "admin");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/employee")
                        .content(new ObjectMapper().writeValueAsString((employeeAdmin)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(10));
    }

    @Test
    void testUpdateEmployee() throws Exception {
        Employee employeeAdmin = new Employee(1l, "admin", "admin", "admin");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/employee/{id}", 1)
                        .content(new ObjectMapper().writeValueAsString((employeeAdmin)))
                        .contentType(MediaType.APPLICATION_JSON)
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1))
                .andExpect(MockMvcResultMatchers.jsonPath("$.firstName").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.lastName").value("admin"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.career").value("admin"));
    }


    @Test
    public void deleteEmployeeAPI() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.delete("/employee/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").exists())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1));
    }

}
