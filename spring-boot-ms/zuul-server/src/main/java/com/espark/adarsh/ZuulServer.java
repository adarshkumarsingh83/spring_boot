package com.espark.adarsh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.actuate.system.ApplicationPidFileWriter;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.cloud.netflix.zuul.EnableZuulServer;

@SpringBootApplication
//@EnableZuulServer
@EnableZuulProxy
public class ZuulServer {

    public static void main(String[] args) {
        SpringApplication zuulServer = new SpringApplication(ZuulServer.class);
        zuulServer.addListeners(new ApplicationPidFileWriter("zuul-server.pid"));
        zuulServer.run(args);
    }

}
