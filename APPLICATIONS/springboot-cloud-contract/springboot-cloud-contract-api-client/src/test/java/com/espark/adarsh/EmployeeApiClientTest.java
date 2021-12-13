package com.espark.adarsh;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.service.EmployeeApiServiceClient;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpHeaders;
import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.Arrays;
import java.util.Collection;

@Slf4j
@AutoConfigureJson
@AutoConfigureWireMock(port = 8081)
@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = SpringbootApplicationMain.class)
public class EmployeeApiClientTest {

    @Autowired
    ObjectMapper objectMapper;

    String jsonData;

    @Autowired
    EmployeeApiServiceClient employeeApiServiceClient;

    @Test
    public void shouldReturnAllEmployee() throws Exception {
        jsonData = objectMapper.writeValueAsString(Arrays.asList(new Employee(1L, "Adarsh"), new Employee(2l, "Radha")));
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/employees"))
                .willReturn(WireMock.aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE)
                        .withBody(jsonData)
                )
        );
        Collection<Employee> employees = this.employeeApiServiceClient.getAllEmployees();
        BDDAssertions.then(employees).size().isEqualTo(2);
        BDDAssertions.then(employees.iterator().next().getId()).isEqualTo(1L);
        BDDAssertions.then(employees.iterator().next().getName()).isEqualTo("Adarsh");

    }
}
