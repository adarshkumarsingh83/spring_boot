package com.espark.adarsh.repository;

import java.util.List;

import com.espark.adarsh.entity.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    List<Customer> findByLastName(String lastName);

    Customer findById(long id);
}