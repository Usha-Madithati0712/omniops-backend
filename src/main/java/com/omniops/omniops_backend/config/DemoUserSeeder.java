package com.omniops.omniops_backend.config;

import com.omniops.omniops_backend.entity.Role;
import com.omniops.omniops_backend.entity.User;
import com.omniops.omniops_backend.repository.RoleRepository;
import com.omniops.omniops_backend.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DemoUserSeeder {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostConstruct
    public void seedUsers() {

        createUser(
                "CEO",
                "ceo@gmail.com",
                "CEO@123",
                1
        );

        createUser(
                "Admin",
                "admin@gmail.com",
                "Admin@123",
                2
        );

        createUser(
                "CRM Executive",
                "crm@gmail.com",
                "CRM@123",
                3
        );

        createUser(
                "Recruiter",
                "recruiter@gmail.com",
                "Recruit@123",
                5
        );

        createUser(
                "Team Lead",
                "teamlead@gmail.com",
                "Team@123",
                7
        );

        createUser(
                "Coordinator",
                "coordinator@gmail.com",
                "Coord@123",
                8
        );

        createUser(
                "HR Executive",
                "hr@gmail.com",
                "HR@123",
                9
        );

        createUser(
                "Accounts Executive",
                "accounts@gmail.com",
                "Accounts@123",
                11
        );

        createUser(
                "BGV Executive",
                "bgv@gmail.com",
                "BGV@123",
                14
        );

    }
        private void createUser(
            String fullName,
            String email,
            String password,
            Integer roleId) {

        if (userRepository.existsByEmail(email)) {

            System.out.println(email + " already exists.");

            return;
        }

        Role role = roleRepository.findById(roleId)
                .orElseThrow(() ->
                        new RuntimeException("Role not found : " + roleId));

        User user = new User();

        user.setFullName(fullName);

        user.setEmail(email);

        user.setPasswordHash(
                passwordEncoder.encode(password));

        user.setPhone("9999999999");

        user.setUsername(email);

        user.setEmployeeCode(
                "EMP" + roleId + "001");

        user.setRole(role);

        user.setStatus(User.Status.Active);

        userRepository.save(user);

        System.out.println(email + " created successfully.");

    }

}