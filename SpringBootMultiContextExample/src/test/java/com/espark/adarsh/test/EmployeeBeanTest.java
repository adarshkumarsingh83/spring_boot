package com.espark.adarsh.test;


import com.espark.adarsh.bean.EmployeeBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;
import java.net.URL;
import org.junit.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.web.client.TestRestTemplate;
;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class EmployeeBeanTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Before
    public void setUp() throws Exception {
        this.base = new URL("http://localhost:" + port + "/");
    }


    @Test
    public void saveEmployee() throws Exception {
        ResponseEntity<EmployeeBean> response = template.postForEntity(base.toString()+"/emp/save",
                new EmployeeBean("200","radha singh"),EmployeeBean.class);
        EmployeeBean employee = response.getBody();
        assertThat(employee.getEmpId(), equalTo("200"));
    }

    @Test
    public void getEmployee() throws Exception {
        ResponseEntity<EmployeeBean> response = template.getForEntity(base.toString()+"/emp/fetch/100",
                EmployeeBean.class);
        EmployeeBean employee = response.getBody();
        assertThat(employee.getEmpId(), equalTo("100"));
    }

}
