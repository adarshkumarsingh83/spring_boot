package com.espark.adarsh.classs;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;

import static org.junit.Assert.assertNotNull;

@org.junit.runner.RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes= com.espark.adarsh.condition.classs.ConditionOnMissingClassConfig.class)
public class ConditionOnMissingClassConfigTest {

    @org.springframework.beans.factory.annotation.Autowired
    org.springframework.context.ApplicationContext applicationContext;

    @org.junit.Test
    public void testServiceOne() {
        ServiceOne serviceOne = applicationContext.getBean("serviceOne",ServiceOne.class);
        assertNotNull(serviceOne);
    }

    @org.junit.Test(expected= org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
    public void testServiceTwo() {
        ServiceTwo serviceTwo = applicationContext.getBean("serviceTwo",ServiceTwo.class);
        assertNotNull(serviceTwo);
    }

}
