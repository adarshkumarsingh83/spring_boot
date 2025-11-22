package com.espark.adarsh;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.modulith.core.ApplicationModule;
import org.springframework.modulith.core.ApplicationModules;

@SpringBootTest
class SpringbootEventsApplicationTests {

	@Test
	void contextLoads() {
        var am = ApplicationModules.of(SpringbootEventsApplication.class);
        System.out.println("Application Modules: " + am);
	}

}
