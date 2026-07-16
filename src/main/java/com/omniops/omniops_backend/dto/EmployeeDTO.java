package com.omniops.omniops_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class EmployeeDTO {

    private Integer userId;

    private String employeeCode;

    private String fullName;

    private String email;

    private String phone;

    private String department;

    private String designation;

    private String reportingManager;

    private String role;

    private String status;

}