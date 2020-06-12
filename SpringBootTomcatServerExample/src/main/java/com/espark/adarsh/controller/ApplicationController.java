package com.espark.adarsh.controller;

import com.espark.adarsh.service.WishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class ApplicationController {

	@Autowired
	private WishServiceImpl wishService;

	@RequestMapping("/")
	@ResponseBody
	public String welcome() {
		return this.wishService.getMessage();

	}

}
