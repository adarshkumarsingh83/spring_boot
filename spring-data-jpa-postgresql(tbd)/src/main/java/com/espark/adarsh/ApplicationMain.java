package com.espark.adarsh;

import com.espark.adarsh.persistance.entity.Employee;
import com.espark.adarsh.persistance.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ApplicationMain implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(ApplicationMain.class);

    @Autowired
    private EmployeeRepository repository;

    public static void main(String[] args) {
        SpringApplication.run(ApplicationMain.class, args);
    }

    @Override
    public void run(String... args) {

        log.info("StartApplication...");

        repository.save(new Employee("adarsh"));
        repository.save(new Employee("radha"));
        repository.save(new Employee("amit"));

        System.out.println("\nfindAll()");
        repository.findAll().forEach(x -> System.out.println(x));

        System.out.println("\nfindById(1L)");
        repository.findById(1l).ifPresent(x -> System.out.println(x));

        System.out.println("\nfindByName('adarsh')");
        repository.findByName("Node").forEach(x -> System.out.println(x));

    }

}