package com.espark.adarsh.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class User implements PoolObject {

    String name;
    String email;

    @Override
    public void reset() {
        this.name = null;
        this.email = null;
    }

    public User clone() throws CloneNotSupportedException {
        return (User) super.clone();
    }
}
