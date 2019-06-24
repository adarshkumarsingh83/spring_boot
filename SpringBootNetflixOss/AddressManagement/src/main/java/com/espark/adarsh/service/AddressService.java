package com.espark.adarsh.service;

import com.espark.adarsh.entity.Address;

import java.util.Optional;


public interface AddressService {

    Address createAddress(Address address);

    Address updateAddress(Long addressId,Address address);

    Optional<Address> getAddress(Long addressId);

    Address deleteById(Long addressId);
}
