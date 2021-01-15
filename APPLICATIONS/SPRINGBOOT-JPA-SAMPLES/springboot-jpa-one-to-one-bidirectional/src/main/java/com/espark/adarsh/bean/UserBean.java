package com.espark.adarsh.bean;

import com.espark.adarsh.entities.User;
import com.espark.adarsh.entities.UserProfile;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.io.Serializable;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBean implements Serializable {

    private Long id;

    private String firstName;

    private String lastName;

    private String email;

    private String password;

    private UserProfileBean userProfile;

    public UserBean() {
    }

    public UserBean(User user) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userProfile = null;
    }

    public UserBean(User user, UserProfile userProfile) {
        this.id = user.getId();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
        this.email = user.getEmail();
        this.password = user.getPassword();
        this.userProfile = new UserProfileBean(userProfile);
    }
}
