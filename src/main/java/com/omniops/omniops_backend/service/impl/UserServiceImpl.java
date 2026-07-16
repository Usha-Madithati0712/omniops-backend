package com.omniops.omniops_backend.service.impl;
import com.omniops.omniops_backend.dto.EmployeeDTO;
import java.util.stream.Collectors;
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
import com.omniops.omniops_backend.dto.ClientSignupRequest;
import com.omniops.omniops_backend.repository.RoleRepository;
import java.time.LocalDateTime;
import java.util.List;
import com.omniops.omniops_backend.dto.ResetPasswordRequest;
@Service
public class UserServiceImpl implements UserService {

        @Autowired
        private UserRepository userRepository;

        @Autowired
        private PasswordEncoder passwordEncoder;

        @Autowired
        private JwtService jwtService;
        @Autowired
        private RoleRepository roleRepository;
        @Autowired
        private PermissionService permissionService;

        @Override
        public String register(RegisterRequest request) {

                if (userRepository.existsByEmail(request.getEmail())) {
                        return "Email already exists";
                }

              User user = new User();

// Temporary employee code
user.setEmployeeCode("TEMP_EMP");
                user.setFullName(request.getFullName());
                user.setEmail(request.getEmail());
                user.setPasswordHash(passwordEncoder.encode(request.getPassword()));
               user.setPhone(request.getPhone());

user.setDepartment(request.getDepartment());

user.setDesignation(request.getDesignation());

user.setReportingManager(request.getReportingManager());

Role selectedRole = roleRepository.findById(request.getRoleId())
        .orElseThrow(() -> new RuntimeException("Role not found"));

String roleName = selectedRole.getRoleName();
switch (roleName) {

    case "CEO / Founder":
        user.setDashboardRedirect("/ceoDashboard.html");
        break;

    case "Admin":
        user.setDashboardRedirect("/adminDashboard.html");
        break;

    case "CRM Executive":
    case "CRM Manager":
        user.setDashboardRedirect("/crm/crmDashboard.html");
        break;

    case "Recruiter":
        user.setDashboardRedirect("/crm/Recruitment/recruiterDashboard.html");
        break;

    case "Recruitment Manager":
        user.setDashboardRedirect("/crm/Recruitment/recruitmentManagerDashboard.html");
        break;

    case "Team Lead":
        user.setDashboardRedirect("/crm/Recruitment/coordination/TeamLeads/teamLeadDashboard.html");
        break;

    case "Coordinator":
        user.setDashboardRedirect("/crm/Recruitment/coordination/coordinationDashboard.html");
        break;

    case "HR Executive":
    case "HR Manager":
        user.setDashboardRedirect("/crm/Recruitment/coordination/TeamLeads/BG/Docs/HR/Payroll/hrDashboard.html");
        break;

    case "Accounts Executive":
        user.setDashboardRedirect("/crm/Recruitment/coordination/TeamLeads/BG/Docs/HR/Payroll/Accounts/accountsDashboard.html");
        break;

    case "Payroll Admin":
        user.setDashboardRedirect("/crm/Recruitment/coordination/TeamLeads/BG/Docs/HR/Payroll/payrollDashboard.html");
        break;

    case "BGV Executive":
        user.setDashboardRedirect("/crm/Recruitment/coordination/TeamLeads/BG/Docs/backgroundVerification/bgvDashboard.html");
        break;

    case "Trainer":
        user.setDashboardRedirect("/trainerDashboard.html");
        break;

    case "Employee":
        user.setDashboardRedirect("/employeeDashboard.html");
        break;

    case "Client":
        user.setDashboardRedirect("/crm/Recruitment/coordination/TeamLeads/BG/Docs/backgroundVerification/UserDashboard.html");
        break;

    default:
        user.setDashboardRedirect("/login.html");
}

user.setUsername(request.getEmail().toLowerCase());

Role role = new Role();
                role.setRoleId(request.getRoleId());

                user.setRole(role);

               if(request.getStatus()!=null){

    user.setStatus(User.Status.valueOf(request.getStatus()));

}else{

    user.setStatus(User.Status.Active);

}

                userRepository.save(user);

// Generate Employee Code
String employeeCode = String.format("EMP%05d", user.getUserId());

user.setEmployeeCode(employeeCode);

// Optional: use employee code as username
user.setUsername(employeeCode.toLowerCase());

userRepository.save(user);

return "User Registered Successfully";
        }
@Override
public List<EmployeeDTO> getAllEmployees() {

    return userRepository.findAllByOrderByUserIdDesc()

            .stream()

            .map(user -> new EmployeeDTO(

                    user.getUserId(),

                    user.getEmployeeCode(),

                    user.getFullName(),

                    user.getEmail(),

                    user.getPhone(),

                    user.getDepartment(),

                    user.getDesignation(),

                    user.getReportingManager(),

                    user.getRole().getRoleName(),

                    user.getStatus().name()

            ))

            .collect(Collectors.toList());

}
@Override
public EmployeeDTO getEmployeeById(Integer id){

    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

    return new EmployeeDTO(

            user.getUserId(),
            user.getEmployeeCode(),
            user.getFullName(),
            user.getEmail(),
            user.getPhone(),
            user.getDepartment(),
            user.getDesignation(),
            user.getReportingManager(),
            user.getRole().getRoleName(),
            user.getStatus().name()

    );

}
@Override
public String updateEmployee(Integer id, RegisterRequest request) {

    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

    user.setFullName(request.getFullName());
    user.setEmail(request.getEmail());
    user.setPhone(request.getPhone());
    user.setDepartment(request.getDepartment());
    user.setDesignation(request.getDesignation());
    user.setReportingManager(request.getReportingManager());

    Role role = roleRepository.findById(request.getRoleId())
            .orElseThrow(() -> new RuntimeException("Role not found"));

    user.setRole(role);

    if(request.getStatus()!=null){
        user.setStatus(User.Status.valueOf(request.getStatus()));
    }

    userRepository.save(user);

    return "Employee Updated Successfully";
}
    @Override
public String resetPassword(Integer id, ResetPasswordRequest request) {

    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

    user.setPasswordHash(
            passwordEncoder.encode(request.getNewPassword())
    );

    userRepository.save(user);

    return "Password Reset Successfully";
}
@Override
public String deleteEmployee(Integer id) {

    User user = userRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Employee not found"));

    userRepository.delete(user);

    return "Employee Deleted Successfully";
}
@Override
        public String clientRegister(ClientSignupRequest request) {

                if (userRepository.existsByEmail(request.getEmail())) {

                        return "Email already exists";

                }

                Role clientRole = roleRepository
                                .findByRoleName("Client")
                                .orElseThrow(() -> new RuntimeException("Client role not found"));

                User user = new User();

                user.setFullName(request.getFullName());

                user.setEmail(request.getEmail());

                user.setPhone(request.getPhone());

                user.setPasswordHash(
                                passwordEncoder.encode(request.getPassword()));

                user.setUsername(request.getEmail());

                user.setRole(clientRole);

                user.setStatus(User.Status.Active);

                user.setEmployeeCode("TEMP_CLIENT");

                userRepository.save(user);

                String clientCode = String.format("CLIENT%05d", user.getUserId());

                user.setEmployeeCode(clientCode);

                userRepository.save(user);

                return "Client Registered Successfully";

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
                                        null);
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
                                        null);
                }

                String token = jwtService.generateToken(user.getEmail());

                List<String> permissions = permissionService.getPermissionsByRole(
                                user.getRole().getRoleId());
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
                                permissions);
        }
}