package com.espark.adarsh.bean;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomResponseBean{

    @JsonInclude(JsonInclude.Include.NON_NULL)
    EmployeeBean employeeBean;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    DeptBean deptBean;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    AddressBean addressBean;

}
