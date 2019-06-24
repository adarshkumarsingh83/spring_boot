package com.espark.adarsh.web;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;
import static org.junit.Assert.assertNotNull;

@org.junit.runner.RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(
        classes={com.espark.adarsh.condition.web.ConditionalBeanWebConfig.class}
        ,webEnvironment= org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE)
public class ConditionalBeanWebConfigTest {

    @org.springframework.beans.factory.annotation.Autowired
    org.springframework.context.ApplicationContext applicationContext;

    @org.junit.Test
    public void testServiceOneForWeb() {
        ServiceOne serviceOne = applicationContext.getBean(ServiceOne.class);
        assertNotNull(serviceOne);
    }

    @org.junit.Test(expected= org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
    public void testServiceTwoForWeb() {
        ServiceTwo serviceTwo = applicationContext.getBean(ServiceTwo.class);
        assertNotNull(serviceTwo);
    }

    @org.junit.Test(expected= org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
    public void testServiceOneForNotWeb() {
        ServiceOne serviceOne = applicationContext.getBean(ServiceOne.class);
        assertNotNull(serviceOne);
    }

    @org.junit.Test
    public void testServiceTwoForNotWeb() {
        ServiceTwo serviceTwo = applicationContext.getBean(ServiceTwo.class);
        assertNotNull(serviceTwo);
    }

}
