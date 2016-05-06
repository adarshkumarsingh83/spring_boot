
package com.espark.adarsh.test;

import com.espark.adarsh.TomcatApplicationDriver;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(TomcatApplicationDriver.class)
@WebIntegrationTest(randomPort = true)
@DirtiesContext
public class TomcatSSLApplicationTests {

	@Value("${local.server.port}")
	private int port;

    @Test
    public void testHome() throws Exception {
        SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(
                new SSLContextBuilder()
                        .loadTrustMaterial(null, new TrustSelfSignedStrategy()).build());

        HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
                .build();

        TestRestTemplate testRestTemplate = new TestRestTemplate();
        ((HttpComponentsClientHttpRequestFactory) testRestTemplate.getRequestFactory())
                .setHttpClient(httpClient);
        ResponseEntity<String> entity = testRestTemplate
                .getForEntity("https://localhost:" + this.port, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertTrue( "Result is not Matched with Server Response",entity.getBody().contains("welcome to the application"));
    }

}
