package com.espark.adarsh.construct;

import com.espark.adarsh.ApplicationMain;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.annotation.IfProfileValue;
import org.springframework.test.annotation.ProfileValueSourceConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

@WebAppConfiguration
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = ApplicationMain.class)
@IntegrationTest({"server.port=0"})
@ProfileValueSourceConfiguration(value = EsparkProfileValueSource.class)
@IfProfileValue(name="spring.profiles.active", values={"development","testing"})
public class AbstractTestClass {

    protected static final Logger LOG = LoggerFactory.getLogger(AbstractTestClass.class);

}