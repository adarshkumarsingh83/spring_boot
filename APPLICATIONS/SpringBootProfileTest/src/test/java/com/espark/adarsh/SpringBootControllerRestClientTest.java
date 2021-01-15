package com.espark.adarsh;

import com.espark.adarsh.construct.AbstractTestClass;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import java.net.URL;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

public class SpringBootControllerRestClientTest extends AbstractTestClass {

    @Value("${local.server.port}")
    private int port;

    @Value("${spring.profiles.active}")
    private String profileName;

    private URL base;
    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/application/welcome-json");
        template = new TestRestTemplate();
    }

    @Test
    public void validateResponse() throws Exception {
        ResponseEntity<String> response = template.getForEntity(base.toString(), String.class);
        assertThat(response.getBody(), equalTo(("{\"message\":\"Welcome to the "+profileName+" Profile of Espark "+System.getProperty("user.name")+"\"}")));
        LOG.info("Response ========> " + response);
    }
}
