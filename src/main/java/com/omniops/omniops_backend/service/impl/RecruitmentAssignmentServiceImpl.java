package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.RecruitmentAssignment;
import com.omniops.omniops_backend.repository.RecruitmentAssignmentRepository;
import com.omniops.omniops_backend.service.RecruitmentAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentAssignmentServiceImpl implements RecruitmentAssignmentService {

    private final RecruitmentAssignmentRepository repository;

    @Override
    public RecruitmentAssignment save(RecruitmentAssignment assignment) {
        return repository.save(assignment);
    }

    @Override
    public List<RecruitmentAssignment> findAll() {
        return repository.findAll();
    }

    @Override
    public RecruitmentAssignment findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public RecruitmentAssignment update(Long id, RecruitmentAssignment assignment) {

        RecruitmentAssignment existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruitment Assignment not found"));

        existing.setClientName(assignment.getClientName());
        existing.setRecruiterName(assignment.getRecruiterName());
        existing.setTechnology(assignment.getTechnology());
        existing.setAssignedDate(assignment.getAssignedDate());
        existing.setPriority(assignment.getPriority());
        existing.setStatus(assignment.getStatus());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {

        RecruitmentAssignment existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Recruitment Assignment not found"));

        repository.delete(existing);
    }
}