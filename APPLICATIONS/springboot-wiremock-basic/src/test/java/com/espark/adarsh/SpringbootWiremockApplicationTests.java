package com.espark.adarsh;

import com.github.tomakehurst.wiremock.http.HttpHeader;
import com.github.tomakehurst.wiremock.http.HttpHeaders;
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
        HttpHeaders headers = new HttpHeaders(
                new HttpHeader("Name", "API"),
                new HttpHeader("Type", "MOBILE"),
                new HttpHeader("Content-Type", "application/json")
        );
        wireMockServer.stubFor(get(urlEqualTo("/api/data"))
                .willReturn(aResponse()
                        .withHeaders(headers)
                        .withStatus(200)
                        .withBodyFile("json/data.json")));
        ResponseEntity<String> response = restTemplate.getForEntity("http://localhost:8080/api/data", String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().get("Name").get(0))
                .isEqualTo("API");
        assertThat(response.getHeaders().get("Type").get(0))
                .isEqualTo("MOBILE");
    }

    @Test
    @DisplayName("Post Api Data Endpoint ")
    public void testPostApiDataEndpoint() {
        String data = "{ 'data':'some test data'}";
        HttpHeaders headers = new HttpHeaders(
                new HttpHeader("Name", "API"),
                new HttpHeader("Type", "WEB"),
                new HttpHeader("Content-Type", "application/json")
        );
        wireMockServer.stubFor(post(urlEqualTo("/api/data"))
                .withRequestBody(equalToJson(data))
                .willReturn(aResponse()
                        .withHeaders(headers)
                        .withStatus(200)
                        .withBodyFile("json/data.json")));
        ResponseEntity<String> response = restTemplate.postForEntity("http://localhost:8080/api/data", data, String.class);
        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        assertThat(response.getHeaders().get("Name").get(0))
                .isEqualTo("API");
        assertThat(response.getHeaders().get("Type").get(0))
                .isEqualTo("WEB");
    }

    @AfterEach
    void stopWireMockServer() {
        this.wireMockServer.stop();
    }
}

