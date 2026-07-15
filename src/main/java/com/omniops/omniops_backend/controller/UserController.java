package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.dto.RegisterRequest;
import com.omniops.omniops_backend.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.omniops.omniops_backend.dto.ClientDropdownDTO;
import com.omniops.omniops_backend.entity.User;
import com.omniops.omniops_backend.repository.UserRepository;

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