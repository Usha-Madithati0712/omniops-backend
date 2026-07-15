package com.omniops.omniops_backend.controller;
import com.omniops.omniops_backend.dto.ClientSignupRequest;
import com.omniops.omniops_backend.dto.LoginRequest;
import com.omniops.omniops_backend.dto.LoginResponse;
import com.omniops.omniops_backend.dto.RegisterRequest;
import com.omniops.omniops_backend.service.UserService;
import java.util.Map;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {
        return userService.login(request);
    }

    @PostMapping("/register")
    public String register(@RequestBody RegisterRequest request) {
        return userService.register(request);
    }
    @PostMapping("/client-register")
public ResponseEntity<?> clientRegister(
        @RequestBody ClientSignupRequest request) {

    String response = userService.clientRegister(request);

    Map<String, Object> result = new HashMap<>();

    if ("Client Registered Successfully".equals(response)) {

        result.put("success", true);
        result.put("message", response);

        return ResponseEntity.ok(result);

    }

    result.put("success", false);
    result.put("message", response);

    return ResponseEntity.badRequest().body(result);

}
}
