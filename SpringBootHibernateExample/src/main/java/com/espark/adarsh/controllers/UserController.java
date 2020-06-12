package com.espark.adarsh.controllers;

import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.entity.User;
import com.espark.adarsh.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

@Controller
@RequestMapping(value = "/user")
public class UserController {

    @Qualifier("userRepository")
    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/delete",method = RequestMethod.DELETE)
    @ResponseBody
    public ResponseBean delete(long id) {
        final ResponseBean<User> userResponseBean = new ResponseBean<User>();
        try {
            final User user = new User(id);
            userRepository.delete(user);
            userResponseBean.setMessage("User successfully deleted!");
        } catch (Exception ex) {
            userResponseBean.setMessage(ex.getMessage());
        }
        return userResponseBean;
    }

    @RequestMapping(value = "/get-by-email",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getByEmail(String email) {
        final ResponseBean<User> userResponseBean = new ResponseBean<User>();
        try {
            final User user = userRepository.getByEmail(email);
            userResponseBean.setData(user);
        } catch (Exception ex) {
            userResponseBean.setMessage("User not found");
        }
        return userResponseBean;
    }

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseBean create(@RequestBody User user) {
        final ResponseBean<User> userResponseBean = new ResponseBean<User>();
        try {
            userRepository.save(user);
            userResponseBean.setMessage("User successfully saved!");
        } catch (Exception ex) {
            userResponseBean.setMessage(ex.getMessage());
        }
        return userResponseBean;
    }

    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public ResponseBean getAllUser() {
        final ResponseBean<List<User>> userResponseBean = new ResponseBean<List<User>>();
        try {
            userResponseBean.setData(userRepository.getAll());
            userResponseBean.setMessage("User Found ");
        } catch (Exception ex) {
            userResponseBean.setMessage("User Not Found ");
        }
        return userResponseBean;
    }

}
