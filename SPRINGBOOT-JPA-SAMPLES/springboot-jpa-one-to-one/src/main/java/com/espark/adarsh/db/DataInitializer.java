package com.espark.adarsh.db;

import com.espark.adarsh.entity.Address;
import com.espark.adarsh.entity.Customer;
import com.espark.adarsh.repository.AddressRepository;
import com.espark.adarsh.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class DataInitializer {

    @Autowired
    CustomerRepository repository;

    @Autowired
    AddressRepository addressRepository;

    @PostConstruct
    public void init() {
        addressRepository.save(new Address());
        repository.save(new Customer("Radha", "Singh"));
        repository.save(new Customer("Adarsh", "kumar"));
        repository.save(new Customer("Amit", "kumar"));
        repository.save(new Customer("Chloe", "O'Brian"));
        repository.save(new Customer("Kim", "Bauer"));
        repository.save(new Customer("David", "Palmer"));
        repository.save(new Customer("Michelle", "Dessler"));
    }

}
