package com.espark.adarsh.web;

import com.espark.adarsh.bean.User;
import com.espark.adarsh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/users")
    public User save(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @GetMapping("/users")
    public List list() {
        List<User> userList = new LinkedList<>();
        userRepository.findAll().forEach(user -> userList.add(user));
        return userList;
    }

    @GetMapping("/users/{id}")
    public User getUser(@PathVariable String id) {
        return userRepository.findById(id).get();
    }

    @PutMapping("/users")
    public User update(@RequestBody User user) {
        userRepository.save(user);
        return user;
    }

    @DeleteMapping("/users/{id}")
    public String deleteUser(@PathVariable String id) {
        userRepository.deleteById(id);
        return id;
    }
}
