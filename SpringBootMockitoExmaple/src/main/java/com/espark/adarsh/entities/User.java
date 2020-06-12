package com.espark.adarsh.entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akumar6 on 8/2/16.
 */
public class User {

    private Integer userId;
    private String userName;
    private List<Permission> permissionList=new ArrayList<Permission>();

    public User() {
    }

    public User(Integer userId, String userName, List<Permission> permissionList) {
        this.userId = userId;
        this.userName = userName;
        this.permissionList = permissionList;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<Permission> getPermissionList() {
        return permissionList;
    }

    public void setPermission(Permission permission) {
        this.permissionList.add(permission);
    }
    public void setPermissionList(List<Permission> permissionList) {
        this.permissionList = permissionList;
    }
}
