package com.hazelcast.spring.springaware;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import java.util.concurrent.Future;

/**
 * Example of enabling {@link com.hazelcast.spring.context.SpringAware}
 * with Java config.
 */
public class SpringAwareAnnotationXMLConfig {

    public static void main(String[] args) throws Exception {
        ApplicationContext context = new GenericXmlApplicationContext("applicationContext.xml");

        HazelcastInstance hazelcastInstance = (HazelcastInstance) context.getBean("instance");

        Future<String> future = hazelcastInstance.getExecutorService("test").submit(new SomeTask());
        System.out.println(future.get());

        HazelcastClient.shutdownAll();
        Hazelcast.shutdownAll();
    }
}
