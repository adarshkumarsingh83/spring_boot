package com.espark.adarsh;

import com.espark.adarsh.service.ApplicationService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.client.support.RestGatewaySupport;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.springframework.test.web.client.ExpectedCount.once;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@Slf4j
@SpringBootTest
public class SpringbootMockExternalCallApplicationTests {

    @Value("${service.api.url}")
    String apiUrl;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    ApplicationService applicationService;

    MockRestServiceServer mockServer;

    @BeforeEach
    public void setup() {
        RestGatewaySupport gateway = new RestGatewaySupport();
        gateway.setRestTemplate(restTemplate);
        mockServer = MockRestServiceServer.createServer(gateway);
    }


    @Test
    public void testGetData() {
        String data = "{'id' : 100 , 'name':'adarsh kumar','email':'adarsh@kumar'}";
        mockServer.expect(once(), requestTo(apiUrl))
                .andExpect(method(HttpMethod.GET))
                .andRespond(withSuccess(data
                        , MediaType.APPLICATION_JSON));

        String result = applicationService.getData();
        log.info("testGetData: " + result);

        mockServer.verify();
        assertEquals(data, result);
    }

    @Test
    public void testPostData() {
        String data = "{'id' : 100 , 'name':'adarsh kumar','email':'adarsh@kumar'}";
        mockServer.expect(once(), requestTo(apiUrl))
                .andExpect(method(HttpMethod.POST))
                .andRespond(withSuccess(data, MediaType.APPLICATION_JSON));

        String result = applicationService.setData(data);
        log.info("testPostData: " + result);

        mockServer.verify();
        assertEquals(data, result);
    }
}

