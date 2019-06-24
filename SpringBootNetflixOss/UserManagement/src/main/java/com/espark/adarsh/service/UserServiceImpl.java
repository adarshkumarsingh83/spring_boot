package com.espark.adarsh.service;

import com.espark.adarsh.entity.User;
import com.espark.adarsh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service("userService")
public class UserServiceImpl implements UserService {


    @Autowired
    private UserRepository userRepository;

    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }

    @Override
    public User updateUser(Long userId,User user) {
        return userRepository.save(user);
    }

    @Override
    public Optional<User> getUser(Long userId) {
        return userRepository.findById(userId);
    }

    @Override
    public User deleteById(Long userId) {
        Optional<User> user = userRepository.findById(userId);
        userRepository.deleteById(userId);
        return user.orElse(null);
    }
}
