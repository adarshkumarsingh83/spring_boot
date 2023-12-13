package com.espark.adarsh.service;

import com.espark.adarsh.bean.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ApiResponseService {


    ObjectMapper objectMapper = new ObjectMapper();

    {
        objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
    }

    public ResponseBean getApiResponse() {
        int number = new Random().nextInt(4 - 1) + 1;
        return getApiResponse(number);
    }

    public JsonNode getApiCustomResponse() throws JsonProcessingException {
        int number = new Random().nextInt(4 - 1) + 1;
        ResponseBean responseBean = getApiResponse(number);
        String input = objectMapper.writeValueAsString(responseBean);
        System.out.println(input);
        JsonNode jsonNode = objectMapper.readTree(input);
        return jsonNode;
    }

    public ResponseBean getApiResponse(int number) {
        ResponseBean responseBean = new ResponseBean();
        if (number > 2) {
            EmployeeBean employeeBean = new EmployeeBean();
            employeeBean.setEmpId("01");
            employeeBean.setFirstName("firstName");
            employeeBean.setLastName("lastName");
            responseBean.setEmployeeBean(employeeBean);
            DeptBean deptBean = new DeptBean();
            deptBean.setDeptId("it");
            deptBean.setDeptName("it dept");
            responseBean.setDeptBean(deptBean);
            AddressBean addressBean = new AddressBean();
            addressBean.setAddressId("00");
            addressBean.setStreet("my street");
            addressBean.setCity("myciti");
            responseBean.setAddressBean(addressBean);
        } else if (number > 1) {
            EmployeeBean employeeBean = new EmployeeBean();
            employeeBean.setEmpId("01");
            employeeBean.setFirstName("firstName");
            employeeBean.setLastName("lastName");
            responseBean.setEmployeeBean(employeeBean);
            DeptBean deptBean = new DeptBean();
            deptBean.setDeptId("it");
            deptBean.setDeptName("it dept");
            responseBean.setDeptBean(deptBean);
            responseBean.setAddressBean(new AddressBean());
        } else {
            EmployeeBean employeeBean = new EmployeeBean();
            employeeBean.setEmpId("01");
            employeeBean.setFirstName("firstName");
            employeeBean.setLastName("lastName");
            responseBean.setEmployeeBean(employeeBean);
            responseBean.setDeptBean(new DeptBean());
            responseBean.setAddressBean(new AddressBean());
        }
        return responseBean;
    }
}
