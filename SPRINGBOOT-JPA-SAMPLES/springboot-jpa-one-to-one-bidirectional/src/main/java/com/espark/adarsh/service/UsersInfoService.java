package com.espark.adarsh.service;

import com.espark.adarsh.bean.UserBean;
import com.espark.adarsh.bean.UserProfileBean;
import com.espark.adarsh.entities.User;
import com.espark.adarsh.entities.UserProfile;
import com.espark.adarsh.respository.UserProfileRepository;
import com.espark.adarsh.respository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.LinkedList;
import java.util.List;

@Service
public class UsersInfoService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserProfileRepository userProfileRepository;

    public List<UserBean> getUsers() {
        List<UserBean> users = new LinkedList<>();
        this.userRepository
                .findAll()
                .forEach(user -> users.add(new UserBean(user, user.getUserProfile())));
        return users;
    }

    public List<UserProfileBean> getUserProfiles() {
        List<UserProfileBean> userProfiles = new LinkedList<>();
        this.userProfileRepository
                .findAll()
                .forEach(userProfile -> userProfiles.add(new UserProfileBean(userProfile, userProfile.getUser())));
        return userProfiles;
    }

}
