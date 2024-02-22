package com.espark.adarsh.bean;

import com.espark.adarsh.entities.Gender;
import com.espark.adarsh.entities.User;
import com.espark.adarsh.entities.UserProfile;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserProfileBean implements Serializable {

    private Long id;

    private String phoneNumber;

    private Gender gender;

    private Date dateOfBirth;

    private String address1;

    private String address2;

    private String street;

    private String city;

    private String state;

    private String country;

    private String zipCode;

    private UserBean user;

    public UserProfileBean() {
    }

    public UserProfileBean(UserProfile userProfile) {
        this.id = userProfile.getId();
        this.phoneNumber = userProfile.getPhoneNumber();
        this.gender = userProfile.getGender();
        this.dateOfBirth = userProfile.getDateOfBirth();
        this.address1 = userProfile.getAddress1();
        this.address2 = userProfile.getAddress2();
        this.street = userProfile.getStreet();
        this.city = userProfile.getCity();
        this.state = userProfile.getState();
        this.country = userProfile.getCountry();
        this.zipCode = userProfile.getZipCode();
        this.user = null;
    }

    public UserProfileBean(UserProfile userProfile, User user) {
        this.id = userProfile.getId();
        this.phoneNumber = userProfile.getPhoneNumber();
        this.gender = userProfile.getGender();
        this.dateOfBirth = userProfile.getDateOfBirth();
        this.address1 = userProfile.getAddress1();
        this.address2 = userProfile.getAddress2();
        this.street = userProfile.getStreet();
        this.city = userProfile.getCity();
        this.state = userProfile.getState();
        this.country = userProfile.getCountry();
        this.zipCode = userProfile.getZipCode();
        this.user = new UserBean(user);
    }
}
