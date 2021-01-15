package com.espark.adarsh.property;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;
import com.espark.adarsh.condition.model.ServiceThree;

import static org.junit.Assert.assertNotNull;

@org.junit.runner.RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes={com.espark.adarsh.condition.property.ConditionalBeanPropertyConfig.class})
public class ConditionalBeanPropertyConfigTest {

    @org.springframework.beans.factory.annotation.Autowired
    org.springframework.context.ApplicationContext applicationContext;

    @org.junit.Test
    public void testServiceOne() {
        ServiceOne serviceOne = applicationContext.getBean(ServiceOne.class);
        assertNotNull(serviceOne);
    }

    @org.junit.Test(expected= org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
    public void testServiceTwo() {
        ServiceTwo serviceTwo = applicationContext.getBean(ServiceTwo.class);
        assertNotNull(serviceTwo);
    }

    @org.junit.Test(expected= org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
    public void testServiceThree() {
        ServiceThree serviceThree = applicationContext.getBean(ServiceThree.class);
        assertNotNull(serviceThree);
    }

}
