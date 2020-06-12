/*
 * Copyright (c) 2015 Espark And ©Adarsh Development Services @copyright All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *   - Redistributions of source code must retain the above copyright
 *     notice, this list of conditions and the following disclaimer.
 *
 *   - Redistributions in binary form must reproduce the above copyright
 *     notice, this list of conditions and the following disclaimer in the
 *     documentation and/or other materials provided with the distribution.
 *
 *   - Neither the name of Espark nor the names of its
 *     contributors may be used to endorse or promote products derived
 *     from this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS
 * IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO,
 * THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR
 * PURPOSE ARE DISCLAIMED.  IN NO EVENT SHALL THE COPYRIGHT OWNER OR
 * CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL,
 * EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO,
 * PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR
 * PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF
 * LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING
 * NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.espark.adarsh.controller;

import com.espark.adarsh.bean.Employee;
import com.espark.adarsh.bean.EsparkResponseBean;
import com.espark.adarsh.service.EmployeeService;
import io.swagger.annotations.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@RestController
@RequestMapping("/employee")
@Api(value = "EmployeeController", description = "Employee Operations")
public class EmployeeController {


    @Autowired(required = true)
    private EmployeeService employeeService;

    @ApiOperation(value = "Employee", nickname = "employee", notes = "Return the List of the Employee")
    @ApiResponses(value = {@ApiResponse(code = 200, message = "Successful Response Send"),
            @ApiResponse(code = 404, message = "Employees not found")
            , @ApiResponse(code = 500, message = "Server Error")})
    @RequestMapping(value = "/list"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public
    @ResponseBody
    EsparkResponseBean<List<Employee>> getEmployeeList() {
        return new EsparkResponseBean(employeeService.getEmployeeList());
    }


    @ApiOperation(value = "Employee", nickname = "employee", notes = "Return the the Employee based on EmpId")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Employee not found")
            , @ApiResponse(code = 500, message = "Server Error")})
    @RequestMapping(value = "/id/{empId}"
            , produces = {"application/json"}
            , method = RequestMethod.GET)
    public
    @ResponseBody
    EsparkResponseBean<Employee> getEmployee(@ApiParam(value = "ID of employee", required = true) @PathVariable("empId") String empId) {

        if (empId != null && !empId.isEmpty()) {
            final Employee employee = this.employeeService.getEmployee(empId);
            return new EsparkResponseBean<Employee>(employee);
        }
        return new EsparkResponseBean<Employee>();
    }

    @ApiOperation(value = "Employee", nickname = "employee", notes = "Delete the Employee")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Invalid ID supplied"),
            @ApiResponse(code = 404, message = "Employee not found")
            , @ApiResponse(code = 500, message = "Server Error")})
    @RequestMapping(value = "/delete/{empId}"
            , produces = {"application/json"}
            , method = RequestMethod.DELETE)
    public
    @ResponseBody
    EsparkResponseBean<Employee> deleteEmployee(@ApiParam(value = "ID of employee", required = true) @PathVariable("empId") String empId) {

        if (empId != null && !empId.isEmpty()) {
            final Employee employee = this.employeeService.deleteEmployee(empId);
            return new EsparkResponseBean<Employee>(employee);
        }
        return new EsparkResponseBean<Employee>();
    }

    @ApiOperation(value = "Employee", nickname = "employee", notes = "Create the Employee")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Employee Exist")
            , @ApiResponse(code = 404, message = "Employee Exist")
            , @ApiResponse(code = 500, message = "Server Error")
            , @ApiResponse(code = 200, message = "Employee Created", responseContainer = "EsparkResponseBean")})
    @RequestMapping(value = "/create"
            , produces = {"application/json"}
            , method = RequestMethod.POST)
    public
    @ResponseBody
    EsparkResponseBean<Employee> createEmployee(@RequestBody Employee employee) {
        if (employee != null) {
            if (this.employeeService.createEmployee(employee)) {
                return new EsparkResponseBean<Employee>(employee);
            }
        }
        return new EsparkResponseBean<Employee>();
    }

    @ApiOperation(value = "Employee", nickname = "employee", notes = "Update the Employee")
    @ApiResponses(value = {@ApiResponse(code = 400, message = "Employee Not Exist")
            , @ApiResponse(code = 404, message = "Employee not Exist")
            , @ApiResponse(code = 500, message = "Server Error")
            , @ApiResponse(code = 200, message = "Employee Updated", responseContainer = "EsparkResponseBean")})
    @RequestMapping(value = "/update"
            , produces = {"application/json"}
            , method = RequestMethod.PUT)
    public
    @ResponseBody
    EsparkResponseBean<Employee> updateEmployee(@ApiParam(name = "empId", value = "String to the application", required = true) @RequestParam("empId") String empId
            , @ApiParam(name = "empName", value = "String to the application", required = true) @RequestParam("empName") String empName
            , @ApiParam(name = "empEmail", value = "String to the application", required = true) @RequestParam("empEmail") String empEmail) {
        if (empId != null && empName != null && empEmail != null) {
            final Employee employee = new Employee(empId, empName, empEmail);
            if (this.employeeService.updateEmployee(employee)) {
                return new EsparkResponseBean<Employee>(employee);
            }
        }
        return new EsparkResponseBean<Employee>();
    }

}
