package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

    Optional<User> findByEmail(String email);

    Optional<User> findByEmployeeCode(String employeeCode);

    boolean existsByEmail(String email);

    boolean existsByEmployeeCode(String employeeCode);
@Query("""
SELECT u
FROM User u
WHERE u.role.roleName = 'Client'
ORDER BY u.fullName
""")
List<User> findAllClients();
}
