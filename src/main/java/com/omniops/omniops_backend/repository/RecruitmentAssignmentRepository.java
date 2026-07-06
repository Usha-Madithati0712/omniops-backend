package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.RecruitmentAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecruitmentAssignmentRepository
        extends JpaRepository<RecruitmentAssignment, Long> {

    boolean existsByClientName(String clientName);

}