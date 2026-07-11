package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.Candidate;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CandidateRepository extends JpaRepository<Candidate, Integer> {

    List<Candidate> findByClientClientId(Integer clientId);

}