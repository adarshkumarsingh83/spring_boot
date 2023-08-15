package com.espark.adarsh;

import com.espark.adarsh.config.GraphqlScalarConfiguration;
import com.espark.adarsh.entity.Employee;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Import;
import org.springframework.graphql.test.tester.HttpGraphQlTester;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.servlet.client.MockMvcWebTestClient;
import org.springframework.web.context.WebApplicationContext;

@ExtendWith(SpringExtension.class)
@AutoConfigureMockMvc
@AutoConfigureGraphQlTester
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@Import({GraphqlScalarConfiguration.class})
class EmployeeGraphqQlControllerWebTests {

    private final static String url ="/api/espark/graphql";

    HttpGraphQlTester tester;

     public EmployeeGraphqQlControllerWebTests(WebApplicationContext context) {
                 WebTestClient client =
                 MockMvcWebTestClient.bindToApplicationContext(context)
                         .configureClient()
                         .baseUrl(url)
                         .defaultHeader("name","my-header")
                         .build();
          tester = HttpGraphQlTester.create(client);
    }




    @Test
    void testWebControllerGetEmployeeById(ApplicationContext context) {
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
        Employee employee = this.tester.document(document)
                .execute()
                .path("data.getEmployee")
                .entity(Employee.class)
                .get();
        Assertions.assertTrue(employee.getId() > 0);
        Assertions.assertNotNull(employee.getFirstName() != null);
        Assertions.assertNotNull(employee.getLastName() != null);
    }

}
