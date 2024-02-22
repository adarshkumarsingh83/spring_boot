package com.espark.adarsh.controller;

import com.espark.adarsh.service.InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/espark")
public class InfoController {

    @Qualifier(value = "infoService")
    @Autowired(required = true)
    private InfoService service;

    @RequestMapping("/info")
    public
    @ResponseBody
    String userInfo(Authentication authentication) {
        String msg = "";
        for (GrantedAuthority authority : authentication.getAuthorities()) {
            String role = authority.getAuthority();
            msg += service.getMsg() + authentication.getName() + ", You have " + role;
        }
        return msg;
    }
}	