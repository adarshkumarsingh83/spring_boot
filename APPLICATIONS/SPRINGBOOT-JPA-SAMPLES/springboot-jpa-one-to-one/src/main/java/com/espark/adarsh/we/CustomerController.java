package com.espark.adarsh.we;

import com.espark.adarsh.entity.Customer;
import com.espark.adarsh.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/customers")
    public List<Customer> getAll() {
        return this.customerService.getAllCustomer();
    }

    @GetMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Long id) {
        return this.customerService.getCustomer(id);
    }

    @PutMapping("/customer/{id}")
    public Customer getCustomer(@PathVariable("id") Long id, @RequestBody Customer customer) {
        return this.customerService.updateCustomer(customer);
    }

    @DeleteMapping("/customer/{id}")
    public Customer deleteCustomer(@PathVariable("id") Long id) {
        return this.customerService.deleteCustomer(id);
    }

    @PostMapping("/customer")
    public Customer getCustomer(@RequestBody Customer customer) {
        return this.customerService.createCustomer(customer);
    }

}
