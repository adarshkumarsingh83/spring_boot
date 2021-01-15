package com.espark.adarsh.controllers;

import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.persistence.entities.Address;
import com.espark.adarsh.persistence.entities.Employee;
import com.espark.adarsh.persistence.repository.AddressRepository;
import com.espark.adarsh.persistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping(value = "/save"
            , method = RequestMethod.POST
            , produces = {"application/json", "application/xml"}
            , consumes = {"application/json", "application/xml"})
    public
    @ResponseBody
    ResponseBean<Address> createAddress(@RequestBody Address address) {
        final ResponseBean<Address> addressResponseBean = new ResponseBean<Address>();
        try {
            addressResponseBean.setData(this.addressRepository.save(address));
            addressResponseBean.setMessage("AddressBean Saved");
        } catch (Exception e) {
            addressResponseBean.setMessage("AddressBean Not Saved " + e.getMessage());
        }
        return addressResponseBean;
    }


    @RequestMapping(value = "/list"
            , method = RequestMethod.GET
            , produces = {"application/json", "application/xml"})
    public
    @ResponseBody
    ResponseBean<List<Address>> getAllAddress() {
        final ResponseBean<List<Address>> addressResponseBean = new ResponseBean<List<Address>>();
        try {
            final List<Address> addressList = (List<Address>) this.addressRepository.findAll();
            for (Address address : addressList) {
                address.getEmployee().setAddress(null);
            }
            addressResponseBean.setData(addressList);
            addressResponseBean.setMessage("AddressBean Found ");
        } catch (Exception e) {
            addressResponseBean.setMessage("AddressBean Not Found " + e.getMessage());
        }
        return addressResponseBean;
    }

    @RequestMapping(value = "/delete/{addressId}"
            , method = RequestMethod.GET
            , produces = {"application/json", "application/xml"})
    public
    @ResponseBody
    ResponseBean<Address> deleteAddress(@PathVariable("addressId") String addressId) {
        final ResponseBean<Address> addressResponseBean = new ResponseBean<Address>();
        try {
            final Address address = this.addressRepository.findByAddressId(Integer.parseInt(addressId));
            address.getEmployee().setAddress(null);
            addressResponseBean.setData(address);
            this.addressRepository.delete(address);
            addressResponseBean.setMessage("AddressBean Deleted ");
        } catch (Exception e) {
            addressResponseBean.setMessage("AddressBean Not Deleted " + e.getMessage());
        }
        return addressResponseBean;
    }


    @RequestMapping(value = "/get/{addressId}"
            , method = RequestMethod.GET
            , produces = {"application/json", "application/xml"})
    public
    @ResponseBody
    ResponseBean<Address> getAddress(@PathVariable("addressId") String addressId) {
        final ResponseBean<Address> addressResponseBean = new ResponseBean<Address>();
        try {
            final Address address = this.addressRepository.findByAddressId(Integer.parseInt(addressId));
            address.getEmployee().setAddress(null);
            addressResponseBean.setData(address);
            addressResponseBean.setMessage("AddressBean Found ");
        } catch (Exception e) {
            addressResponseBean.setMessage("AddressBean Not Found " + e.getMessage());
        }
        return addressResponseBean;
    }
}
