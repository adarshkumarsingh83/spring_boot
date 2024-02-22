package com.espark.adarsh.bean;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;
import com.espark.adarsh.condition.model.ServiceThree;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

@org.junit.runner.RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes= com.espark.adarsh.condition.bean.ConditionalOnMissingBeanConfig.class)
public class ConditionalOnMissingBeanConfigTest {

    @org.springframework.beans.factory.annotation.Autowired
    org.springframework.context.ApplicationContext applicationContext;

    @org.junit.Test
    public void testBeanA() {
        ServiceOne serviceOne = applicationContext.getBean("serviceOne",ServiceOne.class);
        assertNotNull(serviceOne);
    }

    @org.junit.Test(expected= org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
    public void testBeanB() {
        ServiceTwo serviceTwo = applicationContext.getBean("serviceTwo",ServiceTwo.class);
        assertNull(serviceTwo);
    }

    @org.junit.Test
    public void testBeanC() {
        ServiceThree serviceThree = applicationContext.getBean("serviceThree",ServiceThree.class);
        assertNotNull(serviceThree);
    }

}
