package com.espark.adarsh.service;

import com.espark.adarsh.entity.Address;
import com.espark.adarsh.repository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("addressService")
public class AddressServiceImpl implements AddressService {


    @Autowired
    private AddressRepository addressRepository;

    @Override
    public Address createAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long addressId,Address address) {
        return addressRepository.save(address);
    }

    @Override
    public Optional<Address> getAddress(Long addressId) {
        return addressRepository.findById(addressId);
    }

    @Override
    public Address deleteById(Long addressId) {
        Optional<Address> address = addressRepository.findById(addressId);
        addressRepository.deleteById(addressId);
        return address.orElse(null);
    }
}
