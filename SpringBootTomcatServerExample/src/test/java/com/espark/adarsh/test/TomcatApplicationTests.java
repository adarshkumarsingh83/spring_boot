
package com.espark.adarsh.test;

import com.espark.adarsh.TomcatApplicationDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.StreamUtils;
import org.springframework.web.client.RestTemplate;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;
import java.util.zip.GZIPInputStream;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TomcatApplicationDriver.class)
@WebIntegrationTest(randomPort = true)
@DirtiesContext
public class TomcatApplicationTests {

	@Value("${local.server.port}")
	private int port;

	@Test
	public void testHome() throws Exception {
		ResponseEntity<String> entity = new TestRestTemplate()
				.getForEntity("http://localhost:" + this.port, String.class);
		assertEquals(HttpStatus.OK, entity.getStatusCode());
		assertEquals("welcome to the application Adarsh", entity.getBody());
	}

	@Test
	public void testCompression() throws Exception {
		HttpHeaders requestHeaders = new HttpHeaders();
		requestHeaders.set("Accept-Encoding", "gzip");
		HttpEntity<?> requestEntity = new HttpEntity<Object>(requestHeaders);

		RestTemplate restTemplate = new TestRestTemplate();

		ResponseEntity<byte[]> entity = restTemplate.exchange(
				"http://localhost:" + this.port, HttpMethod.GET, requestEntity,
				byte[].class);

		assertEquals(HttpStatus.OK, entity.getStatusCode());

		GZIPInputStream inflater = new GZIPInputStream(
				new ByteArrayInputStream(entity.getBody()));
		try {
			assertEquals("welcome to the application Adarsh",
					StreamUtils.copyToString(inflater, Charset.forName("UTF-8")));
		}
		finally {
			inflater.close();
		}
	}

}
