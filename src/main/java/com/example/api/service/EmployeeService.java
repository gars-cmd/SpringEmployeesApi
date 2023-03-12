package com.example.api.service;

import com.example.api.model.Employee;
import com.example.api.repository.EmployeeProxy;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Data
@Service
public class EmployeeService {

    @Autowired
    private EmployeeProxy employeeProxy;

    public Employee getEmployee(final Integer id) {
        return employeeProxy.getEmployee(id);
    }

    public Iterable<Employee> getEmployees() {
        return employeeProxy.getEmployees();
    }

    public void deleteEmployee(final Integer id) {
        employeeProxy.deleteEmployee(id);
    }

    public Employee saveEmployee(Employee employee) {
        Employee savedEmployee;
        employee.setLast_name(employee.getLast_name().toUpperCase());
        if (employee.getId() == null) {
            savedEmployee = employeeProxy.createEmployee(employee);
        }else {
            savedEmployee = employeeProxy.updateEmployee(employee);
        }
        return savedEmployee;
    }
}
