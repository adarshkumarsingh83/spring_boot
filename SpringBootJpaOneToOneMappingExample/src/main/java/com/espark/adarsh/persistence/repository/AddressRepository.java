package com.espark.adarsh.persistence.repository;



import com.espark.adarsh.persistence.entities.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, Integer> {

    public Address findByAddressId(Integer id);
}
