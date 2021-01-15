package com.espark.adarsh;

import com.espark.adarsh.web.ApplicationController;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */

@SpringBootTest(classes = SpringbootBasicApplication.class,
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class SpringbootBasicApplicationIntegrationTests {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Value("${message}")
    private String message;

    private URL base;
    private ObjectMapper jsonMapper;

    @BeforeEach
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/api/welcome");
        this.jsonMapper = new ObjectMapper();
    }


    @Test
    public void getHello() throws Exception {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(base.toString(), String.class);
        assertEquals(200, responseEntity.getStatusCodeValue());
        final String jsonString = responseEntity.getBody();
        Map<String, String> responseMap = this.jsonMapper.readValue(jsonString, new TypeReference<Map<String, String>>() {
        });
        assertThat(responseMap.get("name"), equalTo(System.getProperty("user.name")));
        assertThat(responseMap.get("msg"), equalTo("Hello " + System.getProperty("user.name") + " " + message));
    }
}
