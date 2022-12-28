package com.espark.adarsh.service;

import com.espark.adarsh.entity.Employee;
import com.espark.adarsh.exception.EmployeeNotFoundException;
import com.espark.adarsh.respository.EmployeeRepository;
import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Field;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Predicate;

@Service
public class EmployeeService {

    @Autowired
    ObservationRegistry registry;

    @Autowired
    EmployeeRepository employeeRepository;

    public List<Employee> getAllEmployee() {
        List<Employee> employeeList = new LinkedList<>();
        this.employeeRepository.findAll().forEach(employee -> employeeList.add(employee));
        return Observation.createNotStarted("get-all-employee", registry)
                .observe(() -> employeeList);
    }

    public Employee getEmployee(Long id) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
        return Observation.createNotStarted("get-employee-by-id", registry)
                .observe(() -> employee);
    }

    public Employee removeEmployee(Long id) {
        Employee employee = this.employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
        this.employeeRepository.deleteById(id);
        return Observation.createNotStarted("delete-employee-by-id", registry)
                .observe(() -> employee);
    }

    public Employee saveEmployee(Employee employeeObj) {
        Employee employee = this.employeeRepository.save(employeeObj);
        return Observation.createNotStarted("save-employee", registry)
                .observe(() -> employee);
    }

    public Employee updateEmployee(Long id, Employee employeeObj) {
        Predicate<Long> predicate =  (empId)->employeeRepository.existsById(empId);
        if(predicate.test(id)) {
            Employee employee = this.employeeRepository.save(employeeObj);
            return Observation.createNotStarted("update-employee", registry)
                    .observe(() -> employee);
        }
         throw new  EmployeeNotFoundException("employee not found");
    }

    public Employee updatePartialEmployee(@PathVariable("id") Long id, Map<String, Object> employee) {
        final Optional<Employee> employeeOptional = this.employeeRepository.findById(id);
        if (employeeOptional.isPresent()) {
            employee.forEach((key, value) -> {
                Field field = ReflectionUtils.findField(Employee.class, key);
                field.setAccessible(true);
                ReflectionUtils.setField(field, employeeOptional.get(), value);
            });
            Employee employee1 =  this.employeeRepository.save(employeeOptional.get());
            return Observation.createNotStarted("update-partial-employee", registry)
                    .observe(() -> employee1);
        }
        return employeeOptional.orElseThrow(() -> new EmployeeNotFoundException("employee not found"));
    }

}
