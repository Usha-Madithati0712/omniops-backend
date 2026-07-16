package com.omniops.omniops_backend.service;
import java.util.List;
import com.omniops.omniops_backend.dto.ClientSignupRequest;
import com.omniops.omniops_backend.dto.EmployeeDTO;
import com.omniops.omniops_backend.dto.LoginRequest;
import com.omniops.omniops_backend.dto.LoginResponse;
import com.omniops.omniops_backend.dto.RegisterRequest;
import com.omniops.omniops_backend.dto.ResetPasswordRequest;
public interface UserService {

    LoginResponse login(LoginRequest request);

    String register(RegisterRequest request);
String clientRegister(ClientSignupRequest request);
List<EmployeeDTO> getAllEmployees();
EmployeeDTO getEmployeeById(Integer id);
String updateEmployee(Integer id, RegisterRequest request);
String resetPassword(Integer id, ResetPasswordRequest request);
String deleteEmployee(Integer id);
}
