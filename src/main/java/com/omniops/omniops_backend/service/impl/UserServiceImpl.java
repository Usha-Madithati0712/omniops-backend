package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.dto.LoginRequest;
import com.omniops.omniops_backend.dto.LoginResponse;
import com.omniops.omniops_backend.dto.RegisterRequest;
import com.omniops.omniops_backend.entity.Role;
import com.omniops.omniops_backend.entity.User;
import com.omniops.omniops_backend.repository.UserRepository;
import com.omniops.omniops_backend.security.JwtService;
import com.omniops.omniops_backend.service.PermissionService;
import com.omniops.omniops_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtService jwtService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email already exists";
        }

        if (userRepository.existsByEmployeeCode(request.getEmployeeCode())) {
            return "Employee Code already exists";
        }

        User user = new User();

        user.setEmployeeCode(request.getEmployeeCode());
        user.setFullName(request.getFullName());
        user.setEmail(request.getEmail());
        user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
user.setUsername(request.getEmployeeCode().toLowerCase());
        Role role = new Role();
        role.setRoleId(request.getRoleId());

        user.setRole(role);

        user.setStatus(User.Status.Active);

        userRepository.save(user);

        return "User Registered Successfully";
    }

    @Override
    public LoginResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElse(null);

        if (user == null) {
            return new LoginResponse(
                    false,
                    "Invalid Email",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }

        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPasswordHash())) {

            return new LoginResponse(
                    false,
                    "Invalid Password",
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );
        }

        String token = jwtService.generateToken(user.getEmail());

        List<String> permissions =
                permissionService.getPermissionsByRole(
                        user.getRole().getRoleId()
                );
System.out.println("ROLE ID = " + user.getRole().getRoleId());
System.out.println("PERMISSIONS = " + permissions);
        user.setLastLogin(LocalDateTime.now());

        userRepository.save(user);

        return new LoginResponse(
                true,
                "Login Successful",
                token,
                user.getUserId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().getRoleName(),
                permissions
        );
    }
}