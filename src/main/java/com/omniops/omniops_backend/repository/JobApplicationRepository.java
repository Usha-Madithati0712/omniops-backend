package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.JobApplication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobApplicationRepository
        extends JpaRepository<JobApplication, Long> {

    boolean existsByClientName(String clientName);

}