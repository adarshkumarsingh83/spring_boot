package com.espark.adarsh.client;


import com.espark.adarsh.bean.User;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

@RibbonClient(name="user-management")
@FeignClient(serviceId="user-management")
public interface UserService {

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable("id") Long id);

    @PutMapping("/user/{id}")
    public User updateUser(@PathVariable("id") Long id,@RequestBody User user);

    @PostMapping("/user")
    public User createUser(@RequestBody User user);

    @DeleteMapping("/user/{id}")
    public User deleteUser(@PathVariable("id") Long id);

}
