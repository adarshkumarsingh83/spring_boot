package com.espark.adarsh;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import com.github.tomakehurst.wiremock.WireMockServer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import static com.github.tomakehurst.wiremock.client.WireMock.*;
import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.options;
import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest
public class SpringbootWiremockApplicationTests {

    @Autowired
    RestTemplate restTemplate;

    WireMockServer wireMockServer;

    @BeforeEach
    void configureSystemUnderTest() {
        this.wireMockServer = new WireMockServer(options()
                .bindAddress("127.0.0.1").port(8080)
        );
        wireMockServer.start();
        configureFor("127.0.0.1", 8080);
    }

    @Test
    @DisplayName("Get Api Data Endpoint ")
    public void testGetApiDataEndpoint() {
        wireMockServer.stubFor(get(urlEqualTo("/api/data"))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/data.json")));
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/data", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @Test
    @DisplayName("Post Api Data Endpoint ")
    public void testPostApiDataEndpoint() {
        String data = "{ 'data':'some test data'}";
        wireMockServer.stubFor(post(urlEqualTo("/api/data"))
                .withRequestBody(equalToJson(data))
                .willReturn(aResponse().withHeader("Content-Type", "application/json")
                        .withStatus(200)
                        .withBodyFile("json/data.json")));
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/api/data", data, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
    }

    @AfterEach
    void stopWireMockServer() {
        this.wireMockServer.stop();
    }
}

