package com.espark.adarsh.web;

import com.espark.adarsh.bean.UserBean;
import com.espark.adarsh.bean.UserProfileBean;
import com.espark.adarsh.entities.UserProfile;
import com.espark.adarsh.service.UsersInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ApplicationController {

    @Autowired
    UsersInfoService usersInfoService;

    @GetMapping("/users")
    public List<UserBean> getUsers() {
        return this.usersInfoService.getUsers();
    }

    @GetMapping("/userprofiles")
    public List<UserProfileBean> getUserProfiles() {
        return this.usersInfoService.getUserProfiles();
    }
}
