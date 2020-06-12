package com.espark.adarsh.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.net.InetAddress;
import java.net.UnknownHostException;

@Controller
public class ApplicationController {

    @GetMapping("/welcome")
    public String greeting(@RequestParam(name = "name",
            required = false, defaultValue = "adarsh") String name, Model model) throws UnknownHostException {
        String ip = String.valueOf(InetAddress.getLocalHost());
        model.addAttribute("name", name);
        model.addAttribute("ip", ip);
        return "welcome";
    }

}
