package com.espark.adarsh.filter;

import lombok.Data;

@Data
public class EmployeeFilter {

    private FilterField id;
    private FilterField salary;
    private FilterField carrier;
}
