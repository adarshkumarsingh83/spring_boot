package com.espark.adarsh.service;

import com.espark.adarsh.bean.Address;
import com.espark.adarsh.bean.User;
import com.espark.adarsh.client.AddressService;
import com.espark.adarsh.client.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ApiService {

    @Autowired
    private UserService userService;

    @Autowired
    private AddressService addressService;


    public User get(Long id){
        User user = this.userService.getUser(id);
        Address address=this.addressService.getAddress(id);
        user.setAddress(address);
        return user;
    }

    public User create(User userObject){
        User user = this.userService.createUser(userObject);
        Address address=this.addressService.createAddress(userObject.getAddress());
        user.setAddress(address);
        return user;
    }

    public User update(Long id, User userObject){
        User user = this.userService.updateUser(id,userObject);
        Address address=this.addressService.updateAddress(id,userObject.getAddress());
        user.setAddress(address);
        return user;
    }

    public User delete(Long id){
        User user = this.userService.deleteUser(id);
        Address address=this.addressService.deleteAddress(id);
        user.setAddress(address);
        return user;
    }


}
