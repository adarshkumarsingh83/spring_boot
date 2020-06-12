/*
 * Copyright 2012-2014 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.espark.adarsh.test;

import com.espark.adarsh.TomcatApplicationDriver;
import com.espark.adarsh.controller.ApplicationController;
import com.espark.adarsh.service.WishService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.PropertyPlaceholderAutoConfiguration;
import org.springframework.boot.autoconfigure.web.*;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(NonAutoConfigurationTomcatApplicationDriverTests.NonAutoConfigurationTomcatApplicationDriver.class)
@WebIntegrationTest(randomPort = true)
@DirtiesContext
public class NonAutoConfigurationTomcatApplicationDriverTests {

    @Value("${local.server.port}")
    private int port;

    @Configuration
    @Import({EmbeddedServletContainerAutoConfiguration.class,
            DispatcherServletAutoConfiguration.class,
            ServerPropertiesAutoConfiguration.class, WebMvcAutoConfiguration.class,
            HttpMessageConvertersAutoConfiguration.class,
            PropertyPlaceholderAutoConfiguration.class})
    @ComponentScan(basePackageClasses = {ApplicationController.class,
            WishService.class})
    public static class NonAutoConfigurationTomcatApplicationDriver {

        public static void main(String[] args) throws Exception {
            SpringApplication.run(TomcatApplicationDriver.class, args);
        }

    }

    @Test
    public void testHome() throws Exception {
        ResponseEntity<String> entity = new TestRestTemplate()
                .getForEntity("http://localhost:" + this.port, String.class);
        assertEquals(HttpStatus.OK, entity.getStatusCode());
        assertEquals("welcome to the application Adarsh", entity.getBody());
    }

}
