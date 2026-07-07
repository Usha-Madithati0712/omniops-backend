package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.Interview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Long> {

    boolean existsByClientNameAndCompanyName(String clientName, String companyName);

}