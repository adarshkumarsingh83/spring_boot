package com.espark.adarsh.repository;

import com.espark.adarsh.entities.Permission;
import com.espark.adarsh.entities.User;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by akumar6 on 8/2/16.
 */
@Repository("userRepository")
public class UserRepositoryImpl implements UserRepository {

    @Override
    public boolean saveUser(User user) {
        if (user != null) {
            return Boolean.TRUE;
        }
        return Boolean.FALSE;
    }

    @Override
    public boolean updateUser(User user) {
        if (user != null) {
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteUser(Integer userId) {
        if (userId != null) {
            return true;
        }
        return false;
    }

    @Override
    public User getUser(Integer userId) {
        if (userId != null) {
            return new User(){
                {
                    setUserId(100);
                    setUserName("Adarsh");
                    setPermission(new Permission(100,"admin"));
                }
            };
        }
        return null;
    }

    @Override
    public List<User> getAllUser() {
        return new ArrayList<User>(){
            {
                add( new User(){
                    {
                        setUserId(100);
                        setUserName("Adarsh");
                        setPermission(new Permission(100,"admin"));
                    }
                });

                add( new User(){
                    {
                        setUserId(101);
                        setUserName("amit");
                        setPermission(new Permission(101,"user"));
                    }
                });
            }
        };
    }
}
