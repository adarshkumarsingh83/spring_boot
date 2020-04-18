package com.espark.adarsh.service;

import com.espark.adarsh.entity.Customer;
import com.espark.adarsh.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public Customer getCustomer(Long id) {
        return customerRepository.findById(id).get();
    }

    public List<Customer> getAllCustomer() {
        List<Customer> customerList = new LinkedList<>();
        customerRepository
                .findAll()
                .forEach(customer -> customerList.add(customer));
        return customerList;
    }

    public Customer createCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer updateCustomer(Customer customer) {
        return this.customerRepository.save(customer);
    }

    public Customer deleteCustomer(Long id) {
        Customer customer = customerRepository.findById(id).get();
        customerRepository.delete(customer);
        return customer;
    }

}
