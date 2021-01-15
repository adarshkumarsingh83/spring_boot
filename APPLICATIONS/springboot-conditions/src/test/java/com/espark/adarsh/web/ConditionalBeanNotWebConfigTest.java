package com.espark.adarsh.web;

import static org.junit.Assert.assertNotNull;

@org.junit.runner.RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(
        classes={com.espark.adarsh.condition.web.ConditionalBeanNotWebConfig.class}
        ,webEnvironment= org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK)
public class ConditionalBeanNotWebConfigTest {

    @org.springframework.beans.factory.annotation.Autowired
    org.springframework.context.ApplicationContext applicationContext;

    @org.junit.Test
    public void testServiceOneForWeb() {
        com.espark.adarsh.condition.model.ServiceOne serviceOne = applicationContext.getBean(com.espark.adarsh.condition.model.ServiceOne.class);
        assertNotNull(serviceOne);
    }

    @org.junit.Test(expected= org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
    public void testServiceTwoForWeb() {
        com.espark.adarsh.condition.model.ServiceTwo serviceTwo = applicationContext.getBean(com.espark.adarsh.condition.model.ServiceTwo.class);
        assertNotNull(serviceTwo);
    }

    @org.junit.Test(expected= org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
    public void testServiceOneForNotWeb() {
        com.espark.adarsh.condition.model.ServiceOne serviceOne = applicationContext.getBean(com.espark.adarsh.condition.model.ServiceOne.class);
        assertNotNull(serviceOne);
    }

    @org.junit.Test
    public void testServiceTwoForNotWeb() {
        com.espark.adarsh.condition.model.ServiceTwo serviceTwo = applicationContext.getBean(com.espark.adarsh.condition.model.ServiceTwo.class);
        assertNotNull(serviceTwo);
    }

}
