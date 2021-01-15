package com.espark.adarsh.test;

import com.espark.adarsh.ApplicationMain;
import com.espark.adarsh.bean.HttpRequestCallableBean;
import com.espark.adarsh.service.ParallelTaskService;
import org.asynchttpclient.Response;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {ApplicationMain.class})
@WebAppConfiguration
@ActiveProfiles(profiles = "development")
public class ParallelRequestTest {

    private static final Logger LOGGER= LoggerFactory.getLogger(ParallelRequestTest.class);

    @Autowired(required = true)
     private ParallelTaskService parallelTaskService;


    @Test
    public void testRequest()throws Exception{
        HttpRequestCallableBean employeeList=new HttpRequestCallableBean("http://localhost:9090/application/employee", RequestMethod.GET);

        HttpRequestCallableBean employee=new HttpRequestCallableBean("http://localhost:9090/application/employee/100", RequestMethod.GET);

        HttpRequestCallableBean saveEmployee=new HttpRequestCallableBean("http://localhost:9090/application/save", RequestMethod.POST);
               saveEmployee.setBodyData("{\"employeeId\":\"1000\",\"employeeName\":\"adarsh kumar\",\"employeeEmail\":\"adarsh@kumar\"}");

        HttpRequestCallableBean updateEmployee=new HttpRequestCallableBean("http://localhost:9090/application/update", RequestMethod.PUT);
        updateEmployee.setBodyData("{\"employeeId\":\"1000\",\"employeeName\":\"adarsh kumar1\",\"employeeEmail\":\"adarsh@kumar1\"}");

        final List<HttpRequestCallableBean> callableBeanList=new ArrayList<HttpRequestCallableBean>(){
            {
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
                add(employeeList);
                add(employee);
                add(saveEmployee);
                add(updateEmployee);
            }
        };



        final List<Response> responseList= this.parallelTaskService.executeRequest(callableBeanList);
        responseList.forEach(response -> {
            System.out.println(response.getResponseBody());
            LOGGER.info(response.getResponseBody());
        });
    }
}

