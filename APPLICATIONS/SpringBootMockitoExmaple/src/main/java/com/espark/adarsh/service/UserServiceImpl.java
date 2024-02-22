package com.espark.adarsh.service;

import com.espark.adarsh.entities.Permission;
import com.espark.adarsh.entities.User;
import com.espark.adarsh.repository.PermissionRepository;
import com.espark.adarsh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by akumar6 on 8/2/16.
 */
@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;


    @Override
    public boolean saveUser(User user) {
        final List<Permission> permissionList=this.permissionRepository.getAllPermission();
        user.setPermissionList(permissionList);
        return this.userRepository.saveUser(user);
    }

    @Override
    public boolean updateUser(User user) {
        final List<Permission> permissionList=this.permissionRepository.getAllPermission();
        user.setPermissionList(permissionList);
        return this.userRepository.updateUser(user);
    }

    @Override
    public boolean deleteUser(Integer userId) {
        return this.userRepository.deleteUser(userId);
    }

    @Override
    public User getUser(Integer userId) {
        return this.userRepository.getUser(userId);
    }

    @Override
    public List<User> getAllUser() {
        return this.userRepository.getAllUser();
    }
}
