package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.Lead;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LeadRepository extends JpaRepository<Lead, Integer> {

    long countByLeadStatus(String leadStatus);

}