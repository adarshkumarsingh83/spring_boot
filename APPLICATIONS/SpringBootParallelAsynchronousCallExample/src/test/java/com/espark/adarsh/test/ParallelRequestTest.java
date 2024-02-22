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
        HttpRequestCallableBean callableBean=new HttpRequestCallableBean("http://localhost:9090/application/welcome", RequestMethod.GET);
        final List<HttpRequestCallableBean> callableBeanList=new ArrayList<HttpRequestCallableBean>(){
            {
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
              add(callableBean);
            }
        };
        final List<Response> responseList= this.parallelTaskService.executeRequest(callableBeanList);
        responseList.forEach(response -> {
            System.out.println(response.getResponseBody());
            LOGGER.info(response.getResponseBody());
        });
    }
}

