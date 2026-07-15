package com.omniops.omniops_backend.dto;

import lombok.Data;

@Data
public class ClientSignupRequest {

    private String fullName;
    private String email;
    private String phone;
    private String password;

}