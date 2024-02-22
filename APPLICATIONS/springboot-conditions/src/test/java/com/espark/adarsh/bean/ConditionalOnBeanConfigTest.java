package com.espark.adarsh.bean;

import com.espark.adarsh.condition.model.ServiceOne;
import com.espark.adarsh.condition.model.ServiceTwo;
import com.espark.adarsh.condition.model.ServiceThree;
import com.espark.adarsh.condition.model.PrintService;
import com.espark.adarsh.condition.model.BlackAndWhitePrinterService;
import com.espark.adarsh.condition.model.ColorPrinterService;

import static org.junit.Assert.assertNotNull;

@org.junit.runner.RunWith(org.springframework.test.context.junit4.SpringRunner.class)
@org.springframework.boot.test.context.SpringBootTest(classes= com.espark.adarsh.condition.bean.ConditionalOnBeanConfig.class)
public class ConditionalOnBeanConfigTest {

    @org.springframework.beans.factory.annotation.Autowired
    org.springframework.context.ApplicationContext applicationContext;

    @org.junit.Test
    public void testBean() {
        ServiceOne serviceOne = applicationContext.getBean("serviceOne",ServiceOne.class);
        ServiceTwo serviceTwo = applicationContext.getBean("serviceTwo",ServiceTwo.class);

        PrintService blackAndWhitePrinterService = applicationContext.getBean("blackAndWhitePrinterService",BlackAndWhitePrinterService.class);
        PrintService colorPrinterService = applicationContext.getBean("colorPrinterService",ColorPrinterService.class);

        assertNotNull(serviceOne);
        assertNotNull(serviceTwo);
        assertNotNull(blackAndWhitePrinterService);
        assertNotNull(colorPrinterService);
    }

   @org.junit.Test(expected= org.springframework.beans.factory.NoSuchBeanDefinitionException.class)
   public void testBean1(){
       ServiceOne serviceOne = applicationContext.getBean("serviceOne",ServiceOne.class);
       ServiceThree serviceThree = applicationContext.getBean("serviceThree",ServiceThree.class);

       assertNotNull(serviceOne);
       assertNotNull(serviceThree);
   }

}
