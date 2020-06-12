package com.espark.adarsh.repository;

import com.espark.adarsh.entity.User;

import java.util.List;

/**
 * Created by Adarsh on 1/28/16.
 */
public interface UserRepository {

    void save(User user);

    void delete(User user);

    List<User> getAll();

    User getByEmail(String email);

    User getById(long id);

    void update(User user);
}
