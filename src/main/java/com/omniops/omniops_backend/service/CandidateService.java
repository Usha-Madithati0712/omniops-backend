package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.Candidate;

import java.util.List;

public interface CandidateService {

    Candidate save(Candidate candidate);

    Candidate findById(Integer id);

    List<Candidate> findAll();

    List<Candidate> findByClient(Integer clientId);

    void delete(Integer id);

}