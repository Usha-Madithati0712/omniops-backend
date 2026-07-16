package com.omniops.omniops_backend.controller;
import com.omniops.omniops_backend.dto.EmployeeDTO;

import com.omniops.omniops_backend.dto.RegisterRequest;
import com.omniops.omniops_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.omniops.omniops_backend.dto.ClientDropdownDTO;
import com.omniops.omniops_backend.entity.User;
import com.omniops.omniops_backend.repository.UserRepository;
import com.omniops.omniops_backend.dto.ResetPasswordRequest;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserService userService;
@Autowired
private UserRepository userRepository;
    @PostMapping
    public String createUser(@RequestBody RegisterRequest request){

        return userService.register(request);

    }
    @GetMapping("/employees")
public List<EmployeeDTO> getEmployees() {

    return userService.getAllEmployees();

}
@GetMapping("/employees/{id}")
public ResponseEntity<EmployeeDTO> getEmployeeById(@PathVariable Integer id){

    return ResponseEntity.ok(
            userService.getEmployeeById(id)
    );

}
@PutMapping("/employees/{id}")
public ResponseEntity<String> updateEmployee(
        @PathVariable Integer id,
        @RequestBody RegisterRequest request) {

    return ResponseEntity.ok(
            userService.updateEmployee(id, request)
    );

}
@PutMapping("/employees/{id}/reset-password")
public ResponseEntity<String> resetPassword(
        @PathVariable Integer id,
        @RequestBody ResetPasswordRequest request) {

    return ResponseEntity.ok(
            userService.resetPassword(id, request)
    );

}
@DeleteMapping("/employees/{id}")
public ResponseEntity<String> deleteEmployee(@PathVariable Integer id) {

    return ResponseEntity.ok(
            userService.deleteEmployee(id)
    );

}
@GetMapping("/clients")
public List<ClientDropdownDTO> getAllClients() {

    return userRepository.findAllClients()

            .stream()

            .map(user -> new ClientDropdownDTO(

                    user.getUserId(),

                    user.getFullName()

            ))

            .collect(Collectors.toList());

}

}