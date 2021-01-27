package com.espark.adarsh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@Slf4j
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMain.class)
public class ApplicationMainTests {

	@LocalServerPort
	protected int port;


	private RestTemplate restTemplate= new RestTemplate();

	@Test
	public void test(){
		ResponseEntity<HashMap> responseEntity=restTemplate.getForEntity("http://localhost:"+port+"/message",HashMap.class);
		System.out.println(responseEntity.getBody());
	}

}
