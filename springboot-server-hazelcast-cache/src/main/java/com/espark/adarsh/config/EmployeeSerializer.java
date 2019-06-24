package com.espark.adarsh.config;

import com.espark.adarsh.bean.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.hazelcast.nio.ObjectDataInput;
import com.hazelcast.nio.ObjectDataOutput;
import com.hazelcast.nio.serialization.ByteArraySerializer;

import java.io.IOException;
import java.io.InputStream;

public class EmployeeSerializer implements ByteArraySerializer<Employee> {

    ObjectMapper mapper = new ObjectMapper();

    public int getTypeId() {
        return 5;
    }

    public void write(ObjectDataOutput out, Employee object)
            throws IOException {
        byte[] data = mapper.writeValueAsBytes(object);
        System.out.println("Size is " + data.length);
        out.write(data);
    }

    public Employee read(ObjectDataInput in) throws IOException {
        return mapper.readValue((InputStream) in,
                Employee.class);
    }

    public void destroy() {
    }

    @Override
    public byte[] write(Employee employee) throws IOException {
        return mapper.writeValueAsBytes(employee);
    }

    @Override
    public Employee read(byte[] bytes) throws IOException {
        return mapper.readValue(bytes, Employee.class);
    }
}
