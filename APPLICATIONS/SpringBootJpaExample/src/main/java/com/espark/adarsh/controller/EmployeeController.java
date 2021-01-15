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

import com.espark.adarsh.entities.Employee;
import com.espark.adarsh.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author Adarsh Kumar
 * @author $LastChangedBy: Adarsh Kumar$
 * @version $Revision: 0001 $, $Date:: 1/1/10 0:00 AM#$
 * @Espark @copyright all right reserve
 */
@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {

    @Autowired(required = true)
    @Qualifier(value = "employeeService")
    private EmployeeService employeeService;


    @RequestMapping(value = "/find/{employeeId}"
            , method = RequestMethod.GET
            , produces = {"application/json", "application/xml"})
    public Employee getEmployee(@PathVariable int employeeId) {
        Employee employee = employeeService.findOne(employeeId);
        return employee;
    }

    @RequestMapping(value = "/list"
            , method = RequestMethod.GET
            , produces = {"application/json", "application/xml"})
    public List<Employee> getEmployees() {
        return this.employeeService.findAll();
    }

    @RequestMapping(value = "/delete/{employeeId}", method = RequestMethod.DELETE)
    public Map<String,Object> deleteEmployee(@PathVariable final int employeeId) {
        this.employeeService.delete(employeeId);
        return new HashMap<String,Object>(){
            {
                put("message","Employee Deleted");
                put("employeeId ",employeeId);
            }
        };
    }

    @RequestMapping(value="/save",method = RequestMethod.POST
            ,consumes = {"application/json", "application/xml"}
            , produces = {"application/json", "application/xml"})
    public Map<String,Object> saveEmployee(@RequestBody final Employee employee){
        this.employeeService.saveEmployee(employee);
        return new HashMap<String,Object>(){
            {
              put("message","Employee Saved");
              put("employee",employee);
            }
        };
    }
}
