package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.dto.ClientSignupRequest;
import com.omniops.omniops_backend.dto.LoginRequest;
import com.omniops.omniops_backend.dto.LoginResponse;
import com.omniops.omniops_backend.dto.RegisterRequest;

public interface UserService {

    LoginResponse login(LoginRequest request);

    String register(RegisterRequest request);
String clientRegister(ClientSignupRequest request);

}