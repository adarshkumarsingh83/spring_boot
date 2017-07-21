package com.espark.adarsh.service;

import com.espark.adarsh.entities.User;

import java.util.List;

/**
 * Created by akumar6 on 8/2/16.
 */
public interface UserService {

    public boolean saveUser(User user);

    public boolean updateUser(User user);

    public boolean deleteUser(Integer userId);

    public User getUser(Integer userId);

    public List<User> getAllUser();
}
