package com.espark.adarsh.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.service.EmployeeService;

@RestController
@RequestMapping("/api")
public class EmployeeController {

	private static final Logger LOGGER =LoggerFactory.getLogger(EmployeeController.class);
	
	@Autowired
	private EmployeeService service;
	
	@GetMapping("/employees")
	public List<Employee> getAllEmpployee(@RequestHeader("correlationId") String correlationId){
		LOGGER.info("Inside getAllEmpployee method of EmployeeController");
		return service.getAllEmployee();
	}
}
