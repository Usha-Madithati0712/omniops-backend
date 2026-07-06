package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.Enrollment;
import com.omniops.omniops_backend.repository.EnrollmentRepository;
import com.omniops.omniops_backend.service.EnrollmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Override
    public Enrollment save(Enrollment enrollment) {
        return enrollmentRepository.save(enrollment);
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public Enrollment findById(Long id) {
        return enrollmentRepository.findById(id).orElse(null);
    }

    @Override
    public Enrollment update(Long id, Enrollment enrollment) {

        Enrollment existing =
                enrollmentRepository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setCandidateName(enrollment.getCandidateName());
        existing.setTechnology(enrollment.getTechnology());
        existing.setRecruiterName(enrollment.getRecruiterName());
        existing.setAmountPaid(enrollment.getAmountPaid());
        existing.setEnrollmentDate(enrollment.getEnrollmentDate());

        existing.setTrainerName(enrollment.getTrainerName());
        existing.setBatchNumber(enrollment.getBatchNumber());
        existing.setMode(enrollment.getMode());
        existing.setDuration(enrollment.getDuration());

        existing.setCurrentStatus(enrollment.getCurrentStatus());
        existing.setRemarks(enrollment.getRemarks());

        return enrollmentRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        enrollmentRepository.deleteById(id);
    }
}