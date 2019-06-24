package com.espark.adarsh.client;


import com.espark.adarsh.bean.Address;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@RibbonClient(name="address-management")
@FeignClient(serviceId="address-management")
public interface AddressService {

    @GetMapping("/address/{id}")
    public Address getAddress(@PathVariable("id") Long id);

    @PutMapping("/address/{id}")
    public Address updateAddress(@PathVariable("id") Long id,@RequestBody Address address);

    @PostMapping("/address")
    public Address createAddress(@RequestBody Address address);

    @DeleteMapping("/address/{id}")
    public Address deleteAddress(@PathVariable("id") Long id);
}
