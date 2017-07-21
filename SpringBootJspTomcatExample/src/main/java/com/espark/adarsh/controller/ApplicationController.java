package com.espark.adarsh.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ApplicationController {

	@RequestMapping("/")
	public String helloWorld(Model model) {
		model.addAttribute("messages", "Welcome to the Espark "+System.getProperty("user.name"));
		return "esparkHome";
	}

}
