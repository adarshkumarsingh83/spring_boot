package com.espark.adarsh.consumer;

import com.espark.adarsh.producer.consumer.ApplicationMain;
import com.espark.adarsh.producer.consumer.bean.Employee;
import com.espark.adarsh.producer.consumer.service.IntegrationService;
import org.apache.http.HttpHeaders;
import org.assertj.core.api.BDDAssertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.github.tomakehurst.wiremock.client.WireMock;

@SpringBootTest(classes = ApplicationMain.class)
@RunWith(SpringRunner.class)
@AutoConfigureWireMock(port = 9090)
@AutoConfigureJson
public class ConsumerTestUnit {

    @Autowired
    IntegrationService consumerClient;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    public void clientShouldRetrunPersonForGivenID() throws Exception {
        WireMock.stubFor(WireMock.get(WireMock.urlEqualTo("/employee/1")).willReturn(
                WireMock.aResponse()
                        .withStatus(200)
                        .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                        .withBody(jsonForPerson(new Employee(1, "Adarsh", "Kumar")))));
        BDDAssertions.then(this.consumerClient.getEmployee(1).getFname()).isEqualTo("Adarsh");
    }

    private String jsonForPerson(final Employee person) throws Exception {
        return objectMapper.writeValueAsString(person);
    }

}