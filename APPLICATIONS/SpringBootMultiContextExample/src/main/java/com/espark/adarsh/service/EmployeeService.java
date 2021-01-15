package com.espark.adarsh.service;

import com.espark.adarsh.bean.EmployeeBean;

public interface EmployeeService {

    public EmployeeBean saveEmployee(EmployeeBean employee);

    public EmployeeBean getEmployee(String empId);


}
