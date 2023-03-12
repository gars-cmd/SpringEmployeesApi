package com.example.api.controller;

import com.example.api.model.Employee;
import com.example.api.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;
    @GetMapping("/")
    public String home(Model model) {
        Iterable<Employee> listEmployee = employeeService.getEmployees();
        model.addAttribute("employees", listEmployee);
        return "home";
    }

    @GetMapping("/createEmployee")
    public String createEmployee(Model model){
        Employee employee = new Employee();
        model.addAttribute("employee", employee);
        return "fromNewEmployee";
    }


    @GetMapping("/deleteEmployee/{id}")
    public ModelAndView deleteEmployee(@PathVariable("id") final int id) {
        employeeService.deleteEmployee(id);
        return new ModelAndView("redirect:/");
    }

    @GetMapping("/updateEmployee/{id}")
    public String updateEmployee(@PathVariable final Integer id, Model model){
        Employee employee = employeeService.getEmployee(id);
        model.addAttribute("employee", employee);
        return "formUpdateEmployee";
    }

    @PostMapping("/saveEmployee")
    public ModelAndView saveEmployee(@ModelAttribute Employee employee) {
        if(employee.getId() != null) {
            // Employee from update form has the password field not filled,
            // so we fill it with the current password.
            Employee current = employeeService.getEmployee(employee.getId());
            employee.setPassword(current.getPassword());
        }
        employeeService.saveEmployee(employee);
        return new ModelAndView("redirect:/");
    }

}
