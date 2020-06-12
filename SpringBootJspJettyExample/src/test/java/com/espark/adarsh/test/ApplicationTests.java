

package com.espark.adarsh.test;

import org.junit.Test;
import org.junit.runner.RunWith;
/*import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)*/
import org.springframework.test.annotation.DirtiesContext;
@DirtiesContext
public class ApplicationTests {

	/*@LocalServerPort
	private int port;

	@Test
	public void testJspWithEl() throws Exception {
		ResponseEntity<String> entity = new TestRestTemplate()
				.getForEntity("http://localhost:" + this.port, String.class);
		*//*assertThat(entity.getStatusCode()).isEqualTo(HttpStatus.OK);
		assertThat(entity.getBody()).contains("/resources/text.txt");*//*
	}*/

}
