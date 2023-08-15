package com.espark.adarsh;

import com.espark.adarsh.config.GraphqlScalarConfiguration;
import com.espark.adarsh.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.GraphQlTester;
import org.springframework.test.context.TestPropertySource;


import javax.annotation.PostConstruct;
import java.util.List;

@AutoConfigureGraphQlTester
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Import({GraphqlScalarConfiguration.class})
public class EmployeeGraphqQlControllerTest {


    @Autowired
    GraphQlTester graphQlTester;

    @PostConstruct
    void init() {

    }

    @Test
    void testGetEmployeeById() {
        String document = """
                     query{
                       getEmployee(id:1){
                         id
                         firstName
                         lastName
                         salary
                       }
                     }
                """;
        Employee employee = graphQlTester.document(document)
                .execute()
                .path("data.getEmployee")
                .entity(Employee.class)
                .get();
        Assertions.assertTrue(employee.getId() > 0);
        Assertions.assertNotNull(employee.getFirstName() != null);
        Assertions.assertNotNull(employee.getLastName() != null);
    }

    @Test
    void testSavingEmployee() {
        String document = """                       
                    mutation{
                      saveEmployee(employeeBean:{ id:10,firstName:"sonu",lastName:"singh",career:"it", salary: 3}) {
                        id firstName lastName career salary
                      }
                    }
                """;
        Employee employee = graphQlTester.document(document)
                .execute()
                .path("data.saveEmployee")
                .entity(Employee.class)
                .get();
        Assertions.assertTrue(employee.getId() > 0);
        Assertions.assertNotNull(employee.getFirstName() != null);
        Assertions.assertNotNull(employee.getLastName() != null);
    }


    @Test
    void testFilterEmployee() {
        String document = """                       
                    query {
                       employeesFilter(filter: { salary: { operator: "gt" value: "5" } }) { id firstName lastName salary }\s
                     }
                """;
        List<Employee> employees = graphQlTester.document(document)
                .execute()
                .path("data.employeesFilter[*]")
                .entityList(Employee.class)
                .get();
        Assertions.assertTrue(employees.size() > 0);
        Assertions.assertNotNull(employees.get(0).getId());
        Assertions.assertNotNull(employees.get(0).getFirstName());
    }

    @Test
    void testUpdatingEmployee() {
        String document = """
                mutation{
                  updateEmployee(employeeBean:{ id:10,firstName:"sonu",lastName:"kumar singh",career:"it", salary: 3}) {
                    id firstName lastName career salary
                  }
                }
                """;
        Employee employee = graphQlTester.document(document)
                .execute()
                .path("data.updateEmployee")
                .entity(Employee.class)
                .get();
        Assertions.assertTrue(employee.getId() > 0);
        Assertions.assertNotNull(employee.getFirstName() != null);
        Assertions.assertNotNull(employee.getLastName() != null);
    }


    @Test
    void testGetAllEmployee() {
        String document = """
                query{
                  getAllEmployee{
                    id
                    firstName
                    lastName
                    salary
                  }
                }
                """;
        List<Employee> employees = graphQlTester.document(document)
                .execute()
                .path("data.getAllEmployee[*]")
                .entityList(Employee.class)
                .get();
        Assertions.assertTrue(employees.size() > 0);
        Assertions.assertNotNull(employees.get(0).getId());
        Assertions.assertNotNull(employees.get(0).getFirstName());
    }

    @Test
    void testDeleteEmployee() {
        String document = """
                     mutation{
                           removeEmployee(id: 3){
                             id
                             firstName
                             lastName
                             career
                           }
                         }
                """;
        Employee employee = graphQlTester.document(document)
                .execute()
                .path("data.removeEmployee")
                .entity(Employee.class)
                .get();
        Assertions.assertTrue(employee.getId() > 0);
        Assertions.assertNotNull(employee.getFirstName() != null);
        Assertions.assertNotNull(employee.getLastName() != null);
    }


    @Test()
    void testGetEmployeeNotFoundException() {
        String document = """
                    query{
                      getEmployee(id:10){
                        id
                        firstName
                        lastName
                        salary
                      }
                    }
                """;

        java.lang.AssertionError exception = Assertions.assertThrows(java.lang.AssertionError.class, () -> {
            graphQlTester.document(document)
                    .execute()
                    .path("data.getEmployee")
                    .entity(Employee.class)
                    .get();
        });
        Assertions.assertTrue(exception.getMessage().toString().contains("employee not found"));
    }


}
