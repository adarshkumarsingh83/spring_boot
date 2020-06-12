package com.espark.adarsh.web;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emp")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public @ResponseBody
    EmployeeBean saveEmployee(@RequestBody EmployeeBean employee){
        return this.employeeService.saveEmployee(employee);
    }

    @RequestMapping(value = "/fetch/{id}",method = RequestMethod.GET)
    public @ResponseBody
    EmployeeBean getEmployee(@PathVariable("id")String employeeId){
        return this.employeeService.getEmployee(employeeId);
    }
}


