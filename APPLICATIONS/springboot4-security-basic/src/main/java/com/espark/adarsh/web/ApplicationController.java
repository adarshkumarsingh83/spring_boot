package com.espark.adarsh.web;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
public class ApplicationController {

    @RequestMapping("/")
    public void index(HttpServletRequest request, HttpServletResponse response) throws IOException {
         response.getWriter().write("welcome to the app ");
    }
}
