package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.TrainerAssignment;
import com.omniops.omniops_backend.repository.TrainerAssignmentRepository;
import com.omniops.omniops_backend.service.TrainerAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TrainerAssignmentServiceImpl implements TrainerAssignmentService {

    private final TrainerAssignmentRepository repository;

    @Override
    public TrainerAssignment save(TrainerAssignment trainerAssignment) {

        if (trainerAssignment.getStatus() == null ||
                trainerAssignment.getStatus().trim().isEmpty()) {

            trainerAssignment.setStatus("Assigned");
        }

        return repository.save(trainerAssignment);
    }

    @Override
    public List<TrainerAssignment> findAll() {
        return repository.findAll();
    }

    @Override
    public TrainerAssignment findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public TrainerAssignment update(Long id, TrainerAssignment trainerAssignment) {

        TrainerAssignment existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer Assignment not found"));

        existing.setCandidateName(trainerAssignment.getCandidateName());
        existing.setRecruiterName(trainerAssignment.getRecruiterName());
        existing.setCompanyName(trainerAssignment.getCompanyName());
        existing.setRoleName(trainerAssignment.getRoleName());

        existing.setTrainerName(trainerAssignment.getTrainerName());
        existing.setTechnology(trainerAssignment.getTechnology());
        existing.setBatchNumber(trainerAssignment.getBatchNumber());

        existing.setStartDate(trainerAssignment.getStartDate());
        existing.setEndDate(trainerAssignment.getEndDate());
        existing.setDuration(trainerAssignment.getDuration());

        existing.setMode(trainerAssignment.getMode());
        existing.setStatus(trainerAssignment.getStatus());
        existing.setRemarks(trainerAssignment.getRemarks());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {

        TrainerAssignment existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Trainer Assignment not found"));

        repository.delete(existing);
    }
}