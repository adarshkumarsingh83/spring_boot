package com.espark.adarsh.service;

import com.espark.adarsh.entity.User;

import java.util.Optional;


public interface UserService {
    User createUser(User user);

    User updateUser(Long userId,User user);

    Optional<User> getUser(Long userId);

    User deleteById(Long userId);
}
