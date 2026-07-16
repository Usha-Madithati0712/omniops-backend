package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.Role;
import com.omniops.omniops_backend.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleRepository roleRepository;

    @GetMapping
    public List<Role> getRoles() {
        return roleRepository.findAll();
    }
}