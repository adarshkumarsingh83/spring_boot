package com.espark.adarsh.controllers;

import com.espark.adarsh.bean.EmployeeBean;
import com.espark.adarsh.bean.ResponseBean;
import com.espark.adarsh.persistence.entities.Employee;
import com.espark.adarsh.persistence.repository.AddressRepository;
import com.espark.adarsh.persistence.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @RequestMapping(value = "/save"
            , method = RequestMethod.POST
            , produces = {"application/json", "application/xml"}
            , consumes = {"application/json", "application/xml"})
    public
    @ResponseBody
    ResponseBean<Employee> createEmployee(@RequestBody Employee employee) {
        final ResponseBean<Employee> employeeResponseBean = new ResponseBean<Employee>();
        try {
            employeeResponseBean.setData(this.employeeRepository.save(employee));
            employeeResponseBean.setMessage("EmployeeBean Saved");
        } catch (Exception e) {
            employeeResponseBean.setMessage("EmployeeBean Not Saved " + e.getMessage());
        }
        return employeeResponseBean;
    }

    @RequestMapping(value = "/list"
            , method = RequestMethod.GET
            , produces = {"application/json", "application/xml"})
    public
    @ResponseBody
    ResponseBean<List<Employee>> getAllEmployee() {
        final ResponseBean<List<Employee>> employeeResponseBean = new ResponseBean<List<Employee>>();
        try {
            final List<Employee> employeeList=(List<Employee>)this.employeeRepository.findAll();
            for(Employee e: employeeList){
                e.getAddress().setEmployee(null);
            }
            employeeResponseBean.setData(employeeList);
            employeeResponseBean.setMessage("Employees Found ");
        } catch (Exception e) {
            employeeResponseBean.setMessage("Employees Not Found " + e.getMessage());
        }
        return employeeResponseBean;
    }

    @RequestMapping(value = "/delete/{employeeId}"
            , method = RequestMethod.GET
            , produces = {"application/json", "application/xml"})
    public
    @ResponseBody
    ResponseBean<Employee> deleteEmployee(@PathVariable("employeeId") String employeeId) {
        final ResponseBean<Employee> employeeResponseBean = new ResponseBean<Employee>();
        try {
            final Employee employee = this.employeeRepository.findByEmployeeId(Integer.parseInt(employeeId));
            employee.getAddress().setEmployee(null);
            employeeResponseBean.setData(employee);
            this.employeeRepository.delete(employee);
            employeeResponseBean.setMessage("Employees Deleted ");
        } catch (Exception e) {
            employeeResponseBean.setMessage("Employees Not Deleted " + e.getMessage());
        }
        return employeeResponseBean;
    }


    @RequestMapping(value = "/get/{employeeId}"
            , method = RequestMethod.GET
            , produces = {"application/json", "application/xml"})
    public
    @ResponseBody
    ResponseBean<Employee> getEmployee(@PathVariable("employeeId") String employeeId) {
        final ResponseBean<Employee> employeeResponseBean = new ResponseBean<Employee>();
        try {
            final Employee employee = this.employeeRepository.findByEmployeeId(Integer.parseInt(employeeId));
            employee.getAddress().setEmployee(null);
            employeeResponseBean.setData(employee);
            employeeResponseBean.setMessage("Employees Found ");
        } catch (Exception e) {
            employeeResponseBean.setMessage("Employees Not Found " + e.getMessage());
        }
        return employeeResponseBean;
    }
}
