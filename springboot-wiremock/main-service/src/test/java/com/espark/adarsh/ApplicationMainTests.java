package com.espark.adarsh;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import javax.annotation.PostConstruct;
import java.util.HashMap;


@Slf4j
@SpringBootTest( webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationMain.class)
@ActiveProfiles("test")
public class ApplicationMainTests {

	@LocalServerPort
	protected int port;

	protected RestTemplate restTemplate;

	@PostConstruct
	public void init(){
		this.restTemplate=new RestTemplate();
	}

	@Test
	public void testInDirect(){
		ResponseEntity<HashMap> responseEntity=restTemplate.getForEntity("http://localhost:"+port+"/msg",HashMap.class);
		log.info("Response From Serer "+responseEntity.getBody());

	}

}
