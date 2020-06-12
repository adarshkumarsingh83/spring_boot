package com.espark.adarsh;

import org.junit.Before;
import org.junit.Test;
import org.springframework.cloud.contract.wiremock.WireMockRestServiceServer;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

public class WireMockTest {

    protected RestTemplate restTemplate;

    @Before
    public void contextLoads() throws Exception {
        restTemplate=new RestTemplate();
        MockRestServiceServer server = WireMockRestServiceServer.with(this.restTemplate)
                .baseUrl("http://example.org").stubs("classpath:json/*")
                .build();
        //server.verify();
    }

    @Test
    public void testDirect(){
        ResponseEntity<HashMap> responseEntity=restTemplate.getForEntity("http://example.org/message",HashMap.class);
        System.out.println(responseEntity.getBody());
    }
}
