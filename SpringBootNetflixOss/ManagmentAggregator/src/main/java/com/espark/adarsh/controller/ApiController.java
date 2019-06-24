package com.espark.adarsh.controller;

import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.bean.User;
import com.espark.adarsh.service.ApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class ApiController {

    @Autowired
    private ApiService apiService;

    @GetMapping("/aggregator/{id}")
    public ResponseBean<User> get(@PathVariable("id") Long id) {
        User user = this.apiService.get(id);
        return new ResponseBean<>(user);
    }

    @PostMapping("/aggregator")
    public ResponseBean<User> create(@RequestBody User userObject) {
        User user = this.apiService.create(userObject);
        return new ResponseBean<>(user);
    }

    @PutMapping("/aggregator/{id}")
    public ResponseBean<User> update(@PathVariable("id") Long id, @RequestBody User userObject) {
        User user = this.apiService.update(id, userObject);
        return new ResponseBean<>(user);
    }

    @DeleteMapping("/aggregator/{id}")
    public ResponseBean<User> delete(@PathVariable("id") Long id) {
        User user = this.apiService.delete(id);
        return new ResponseBean<>(user);
    }

}
