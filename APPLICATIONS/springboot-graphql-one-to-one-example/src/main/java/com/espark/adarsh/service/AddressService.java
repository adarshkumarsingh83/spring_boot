package com.espark.adarsh.service;

import com.espark.adarsh.entity.Address;
import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.exception.AddressNotFoundException;
import com.espark.adarsh.filter.AddressFilter;
import com.espark.adarsh.filter.EmployeeFilter;
import com.espark.adarsh.filter.FilterField;
import com.espark.adarsh.respository.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.PathVariable;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class AddressService {



    @Autowired
    AddressRepository addressRepository;

    public List<Address> getAllAddress() {
        List<Address> addresses = new LinkedList<>();
        this.addressRepository.findAll().forEach(employee -> addresses.add(employee));
        return addresses;
    }

    public Address getAddress(Long id) {
        return this.addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("employee not found", id));
    }

    public Address removeAddress(Long id) {
        Address address = this.addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found", id));
        this.addressRepository.deleteById(id);
        return address;
    }

    public Address saveAddress(Address employee) {
        return this.addressRepository.save(employee);
    }

    public Address updateAddress(Long id, Address employee) {
         this.addressRepository.findById(id)
                .orElseThrow(() -> new AddressNotFoundException("Address not found", id));
        return this.addressRepository.save(employee);
    }


    public Address updatePartialAddress(@PathVariable("id") Long id, Map<String, Object> address) {
        final Optional<Address> addressOptional = this.addressRepository.findById(id);
        if (addressOptional.isPresent()) {
            address.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Employee.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, addressOptional.get(), value);
            });
            return this.addressRepository.save(addressOptional.get());
        }
        return addressOptional.orElseThrow(() -> new AddressNotFoundException("Address not found", id));
    }




    public Iterable<Address> addressFilter(AddressFilter filter) {
        Specification<Address> spec = null;

        if (filter.getId() != null)
            spec = (spec == null ? byAddressId(filter.getId()) : spec.and(byAddressId(filter.getId())));

        if (filter.getStreet() != null)
            spec = (spec == null ? byStreet(filter.getStreet()) :
                    spec.and(byStreet(filter.getStreet())));

        if (filter.getState() != null)
            spec = (spec == null ? byStreet(filter.getState()) :
                    spec.and(byState(filter.getState())));

        if (filter.getCountry() != null)
            spec = (spec == null ? byCountry(filter.getCountry()) :
                    spec.and(byCountry(filter.getCountry())));

        if (spec != null)
            return addressRepository.findAll(spec);
        else
            return addressRepository.findAll();
    }


    private Specification<Address> byAddressId(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("id"));
    }

    private Specification<Address> byStreet(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("street"));
    }

    private Specification<Address> byState(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("state"));
    }

    private Specification<Address> byCountry(FilterField filterField) {
        return (root, query, builder) -> filterField.generateCriteria(builder, root.get("country"));
    }
}
