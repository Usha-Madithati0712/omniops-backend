package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.InternalHiring;

import java.util.List;
import java.util.Optional;

public interface InternalHiringService {

    List<InternalHiring> getAllCandidates();

    Optional<InternalHiring> getCandidateById(Integer id);

    InternalHiring saveCandidate(InternalHiring candidate);

    InternalHiring updateCandidate(Integer id, InternalHiring candidate);

    void deleteCandidate(Integer id);

}