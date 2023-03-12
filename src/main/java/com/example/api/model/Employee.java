package com.example.api.model;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Employee {

    private Integer id;

    private String first_name;

    private String last_name;

    private String mail;

    private String password;
}
