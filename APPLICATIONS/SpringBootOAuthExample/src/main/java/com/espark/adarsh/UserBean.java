package com.espark.adarsh;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by adarsh_k on 4/6/2017.
 */
@Entity
class UserBean {

    @Id
    @GeneratedValue
    private Long id;
    private String username;
    private String password;
    private boolean active;

    public UserBean() {
    }

    public UserBean(String username, String password, boolean active) {
        this.username = username;
        this.password = password;
        this.active = active;
    }

    public UserBean(Long id, String username, String passpword, boolean active) {
        this.id = id;
        this.username = username;
        this.password = passpword;
        this.active = active;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
