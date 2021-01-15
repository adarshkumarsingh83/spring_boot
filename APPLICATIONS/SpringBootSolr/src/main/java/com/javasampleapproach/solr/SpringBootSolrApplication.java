package com.javasampleapproach.solr;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.javasampleapproach.solr.model.Customer;
import com.javasampleapproach.solr.repo.CustomerRepository;

@SpringBootApplication
public class SpringBootSolrApplication implements CommandLineRunner {

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public void run(String... args) throws Exception {

		customerRepository.deleteAll();

		// Store customers
		customerRepository.save(Arrays.asList(new Customer("1", "Jack", 20), 
											new Customer("2", "Adam", 24),
											new Customer("3", "Kim", 27), 
											new Customer("4", "David", 30), 
											new Customer("5", "Peter", 21)));

		// Fetch all customers
		System.out.println("--------------------------------");
		System.out.println("Select all Customers:");
		System.out.println("--------------------------------");

		for (Customer product : this.customerRepository.findAll()) {
			System.out.println(product);
		}

		// Find customer by Name ends with 'm'
		System.out.println("--------------------------------");
		System.out.println("Find Customers that have names EndsWith m:");
		System.out.println("--------------------------------");

		for (Customer customer : this.customerRepository.findByNameEndsWith("m")) {
			System.out.println(customer);
		}
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootSolrApplication.class, args);
	}
}