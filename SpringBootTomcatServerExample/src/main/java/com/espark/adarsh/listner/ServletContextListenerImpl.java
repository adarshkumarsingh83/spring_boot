package com.espark.adarsh.listner;

import org.springframework.stereotype.Component;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

@Component
public class ServletContextListenerImpl implements ServletContextListener {

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		System.out.println("*** contextInitialized");
	}

	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		System.out.println("*** contextDestroyed");
	}

}
