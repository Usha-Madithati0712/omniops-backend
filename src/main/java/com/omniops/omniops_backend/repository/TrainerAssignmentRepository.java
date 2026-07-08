package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.TrainerAssignment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TrainerAssignmentRepository
        extends JpaRepository<TrainerAssignment, Long> {

}