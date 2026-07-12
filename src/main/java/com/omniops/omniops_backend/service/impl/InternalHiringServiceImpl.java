package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.InternalHiring;
import com.omniops.omniops_backend.repository.InternalHiringRepository;
import com.omniops.omniops_backend.service.InternalHiringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InternalHiringServiceImpl implements InternalHiringService {

    @Autowired
    private InternalHiringRepository internalHiringRepository;

    @Override
    public List<InternalHiring> getAllCandidates() {
        return internalHiringRepository.findAll();
    }

    @Override
    public Optional<InternalHiring> getCandidateById(Integer id) {
        return internalHiringRepository.findById(id);
    }

    @Override
    public InternalHiring saveCandidate(InternalHiring candidate) {
        return internalHiringRepository.save(candidate);
    }

    @Override
    public InternalHiring updateCandidate(Integer id, InternalHiring candidate) {

        InternalHiring existing = internalHiringRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Candidate not found"));

        existing.setCandidateName(candidate.getCandidateName());
        existing.setAppliedPosition(candidate.getAppliedPosition());
        existing.setSource(candidate.getSource());
        existing.setRecruiter(candidate.getRecruiter());

        existing.setStatus(candidate.getStatus());

        existing.setHrRound(candidate.getHrRound());
        existing.setTechnicalRound(candidate.getTechnicalRound());
        existing.setManagerRound(candidate.getManagerRound());
        existing.setFinalRound(candidate.getFinalRound());

        existing.setOfferDate(candidate.getOfferDate());
        existing.setSalaryOffered(candidate.getSalaryOffered());
        existing.setJoiningDate(candidate.getJoiningDate());
        existing.setOfferStatus(candidate.getOfferStatus());

        existing.setRoleAssignment(candidate.getRoleAssignment());
        existing.setSalarySetup(candidate.getSalarySetup());
        existing.setManagerAssignment(candidate.getManagerAssignment());
        existing.setComplianceCollection(candidate.getComplianceCollection());
        existing.setAccountCreation(candidate.getAccountCreation());

        return internalHiringRepository.save(existing);
    }

    @Override
    public void deleteCandidate(Integer id) {
        internalHiringRepository.deleteById(id);
    }
}