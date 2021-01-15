package com.espark.adarsh.web;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/emp")
@RestController
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/save",method = RequestMethod.POST)
    public @ResponseBody
    Employee saveEmployee(@RequestBody Employee employee){
        return this.employeeService.saveEmployee(employee);
    }

    @RequestMapping(value = "/fetch/{id}",method = RequestMethod.GET)
    public @ResponseBody  Employee getEmployee(@PathVariable("id")String employeeId){
        return this.employeeService.getEmployee(employeeId);
    }
}
