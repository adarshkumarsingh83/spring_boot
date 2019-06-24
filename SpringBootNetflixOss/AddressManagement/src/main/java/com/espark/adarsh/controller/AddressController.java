package com.espark.adarsh.controller;

import com.espark.adarsh.entity.Address;
import com.espark.adarsh.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.PostConstruct;

@RestController
public class AddressController {

   // private final SpanAccessor spanAccessor;

   @Autowired
    private AddressService addressService;

   @PostConstruct
   public void init() {
       //Span span = this.spanAccessor.getCurrentSpan();
       //this.log.info(String.format("traceId: %s, spanId: %s",
        //       span.getTraceId(), span.getSpanId()));
   }

    @PostMapping("/address")
    public Address create(@RequestBody Address address){
        return addressService.createAddress(address);
    }

    @PutMapping("/address/{id}")
    public Address update(@PathVariable("id") Long addressId,@RequestBody Address address){
        return addressService.updateAddress(addressId,address);
    }

    @GetMapping("/address/{id}")
    public Address get(@PathVariable("id") Long addressId){
        return addressService.getAddress(addressId).orElse(null);
    }

    @DeleteMapping("/address/{id}")
    public Address delete(@PathVariable("id") Long addressId){
        return addressService.deleteById(addressId);
    }
}
