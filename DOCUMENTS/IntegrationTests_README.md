# Integration tests

---

* uses @SpringBootTest annotation which loads the actual application context.
* uses WebEnvironment.RANDOM_PORT to create run the application at some random server port.
* @LocalServerPort gets the reference of port where the server has started. It helps in building the actual request URIs to mimic real client interactions.
* Use TestRestTemplate class helps in invoking the HTTP requests which are handled by controller class.
* @Sql annotation helps in populating database with some prerequisite data if test is dependent on it to test the behavior correctly.
* org.junit.jupiter.api.Test annotations are from Junit 5 and mark the method as test method to run.

* XxxxIntegationTest.java
```
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.jdbc.Sql;
import com.adarsh.web.bean.XxxBean;

 
@SpringBootTest(classes = XxxxApplicationMain.class, 
        webEnvironment = WebEnvironment.RANDOM_PORT)
public class XxxxControllerIntegrationTests {

    @LocalServerPort
    private int port;
 
    @Autowired
    private TestRestTemplate restTemplate;
 
    @Sql({ "schema.sql", "data.sql" })
    @Test
    public void testAllXxxx() 
    {
        assertTrue(
                this.restTemplate
                    .getForObject("http://localhost:" + port + "/xxxx", XxxBean.class)
                    .getXxxBeanList().size() == 3);
    }
 
    @Test
    public void testAddXxxx) {
        XxxBean xXBean = new XxxBean("xxx", "xxx", "xxxx@gmail.com");
        ResponseEntity<String> responseEntity = this.restTemplate
            .postForEntity("http://localhost:" + port + "/xxxx", xXBean, String.class);
        assertEquals(201, responseEntity.getStatusCodeValue());
    }
}

````