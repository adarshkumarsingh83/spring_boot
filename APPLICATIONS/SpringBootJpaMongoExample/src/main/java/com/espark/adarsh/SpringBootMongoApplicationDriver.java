/*
 * Copyright 2012-2015 the original author or authors.
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

package com.espark.adarsh;

import com.espark.adarsh.entites.Employee;
import com.espark.adarsh.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootMongoApplicationDriver implements CommandLineRunner {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public void run(String... args) throws Exception {
		this.repository.deleteAll();

		// save a couple of customers
		this.repository.save(new Employee("Adarsh", "Kumar"));
		this.repository.save(new Employee("Radha", "Singh"));

		// fetch all customers
		System.out.println("Customers found with findAll():");
		System.out.println("-------------------------------");
		for (Employee employee : this.repository.findAll()) {
			System.out.println(employee);
		}
		System.out.println();

		// fetch an individual customer
		System.out.println("Employee found with findByFirstName('Adarsh'):");
		System.out.println("--------------------------------");
		System.out.println(this.repository.findByFirstName("Adarsh"));

		System.out.println("Customers found with findByLastName('Singh'):");
		System.out.println("--------------------------------");
		for (Employee employee : this.repository.findByLastName("Singh")) {
			System.out.println(employee);
		}
	}

	public static void main(String[] args) throws Exception {
		SpringApplication.run(SpringBootMongoApplicationDriver.class, args);
	}

}
