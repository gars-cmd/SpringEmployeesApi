package com.example.api.repository;

import com.example.api.CustomProperties;
import com.example.api.model.Employee;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;



@Slf4j
@Component
public class EmployeeProxy  {

    @Autowired
    private CustomProperties properties;


    public Iterable<Employee> getEmployees() {
        String baseApiUrl = properties.getApiUrl();
        String getEmployeesUrl = baseApiUrl + "/employees";

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Iterable<Employee>> response = restTemplate.exchange(
                getEmployeesUrl,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<Iterable<Employee>>() {}
        );

        log.debug("Get Employees call" + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee createEmployee(Employee e) {
        String baseApiUrl = properties.getApiUrl();
        String createEmployeeUrl = baseApiUrl + "/employee";

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(e);
        ResponseEntity<Employee> response = restTemplate.exchange(
                createEmployeeUrl,
                HttpMethod.POST,
                request,
                Employee.class
        );

        log.debug("Create Employee call " + response.getStatusCode().toString());

        return response.getBody();
    }

    public Employee getEmployee(Integer id) {
        String baseApiUrl = properties.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Employee> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.GET,
                null,
                Employee.class
        );

        log.debug("Get Employee call" + response.getStatusCode().toString());

        return response.getBody();
    }

    public void deleteEmployee(final Integer id) {
        String baseApiUrl = properties.getApiUrl();
        String getEmployeeUrl = baseApiUrl + "/employee/" + id;

        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<Void> response = restTemplate.exchange(
                getEmployeeUrl,
                HttpMethod.DELETE,
                null,
                Void.class
        );
        log.debug("Delete Employee call" + response.getStatusCode().toString());
    }

    public Employee updateEmployee(Employee employee) {
        String baseApiUrl = properties.getApiUrl();
        String updateEmployeeUrl = baseApiUrl + "/employee/" + employee.getId();

        RestTemplate restTemplate = new RestTemplate();
        HttpEntity<Employee> request = new HttpEntity<Employee>(employee);
        ResponseEntity<Employee> response = restTemplate.exchange(
                updateEmployeeUrl,
                HttpMethod.PUT,
                null,
                Employee.class
        );

        log.debug("Update Employee call" + response.getStatusCode().toString());
        return response.getBody();
    }
}
