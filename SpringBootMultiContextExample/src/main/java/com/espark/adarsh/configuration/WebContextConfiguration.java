package com.espark.adarsh.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Configuration
public class WebContextConfiguration {


    @Value("${environments.${spring.profiles.active}.url1}")
    String apiUrl1;

    @Value("${environments.${spring.profiles.active}.url2}")
    String apiUrl2;

    @Value("${environments.${spring.profiles.active}.url3}")
    String apiUrl3;

    @Bean
    public ServletRegistrationBean apiV() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(DispatcherServlet.class);
        dispatcherServlet.setApplicationContext(applicationContext);

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, "/");
        servletRegistrationBean.setName("api-v0");
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean apiV1() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(DispatcherServlet.class);
        dispatcherServlet.setApplicationContext(applicationContext);

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, apiUrl1);
        servletRegistrationBean.setName("api-v1");
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean apiV2() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(DispatcherServlet.class);
        dispatcherServlet.setApplicationContext(applicationContext);

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, apiUrl2);
        servletRegistrationBean.setName("api-v2");
        return servletRegistrationBean;
    }

    @Bean
    public ServletRegistrationBean apiV3() {
        DispatcherServlet dispatcherServlet = new DispatcherServlet();

        AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(DispatcherServlet.class);
        dispatcherServlet.setApplicationContext(applicationContext);

        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(dispatcherServlet, apiUrl3);
        servletRegistrationBean.setName("api-v3");
        return servletRegistrationBean;
    }
}
