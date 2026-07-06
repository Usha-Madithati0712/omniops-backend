package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    boolean existsByCandidateName(String candidateName);

}