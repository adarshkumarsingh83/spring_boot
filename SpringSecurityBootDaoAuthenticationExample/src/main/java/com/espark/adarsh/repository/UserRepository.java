package com.espark.adarsh.repository;

import com.espark.adarsh.bean.UserBean;

/**
 * Created by Adarsh on 1/28/16.
 */
public interface UserRepository {

    public UserBean getUserInfo(String username);
}
