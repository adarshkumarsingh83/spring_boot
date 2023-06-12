package com.espark.adarsh.bean;

import com.espark.adarsh.entity.Address;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotEmpty;

public class AddressBean {

    @Digits(fraction = 0, integer = 100, message = "id for address")
    private Long id;

    @NotEmpty
    private String street;
    @NotEmpty
    private String state;

    @NotEmpty
    private String country;

    public AddressBean() {
    }

    public AddressBean(Long id, String street, String state, String country) {
        this.id = id;
        this.street = street;
        this.state = state;
        this.country = country;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Address getAddress(){
        return new Address(this.id,this.street,this.state,this.country);
    }
}
