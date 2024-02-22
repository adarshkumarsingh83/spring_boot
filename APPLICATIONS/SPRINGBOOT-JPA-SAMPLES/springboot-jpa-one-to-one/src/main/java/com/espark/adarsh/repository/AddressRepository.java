package com.espark.adarsh.repository;

import com.espark.adarsh.entity.Address;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface AddressRepository extends CrudRepository<Address, Long> {

    List<Address> findByLastName(String lastName);

    Address findById(long id);
}