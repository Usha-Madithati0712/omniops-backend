package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.RecruitmentAssignment;
import com.omniops.omniops_backend.entity.JobApplication;
import com.omniops.omniops_backend.repository.RecruitmentAssignmentRepository;
import com.omniops.omniops_backend.repository.JobApplicationRepository;
import com.omniops.omniops_backend.service.RecruitmentAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RecruitmentAssignmentServiceImpl implements RecruitmentAssignmentService {

    private final RecruitmentAssignmentRepository repository;
private final JobApplicationRepository jobApplicationRepository;
 @Override
public RecruitmentAssignment save(RecruitmentAssignment assignment) {

    if (assignment.getStatus() == null ||
            assignment.getStatus().trim().isEmpty()) {

        assignment.setStatus("Assigned");
    }

    RecruitmentAssignment saved = repository.save(assignment);

    boolean exists = jobApplicationRepository.existsByClientName(
            saved.getClientName()
    );

    if (!exists) {

        JobApplication application = new JobApplication();

        application.setClientName(saved.getClientName());

        application.setRecruiterName(saved.getRecruiterName());

        application.setRoleApplied("");

        application.setCompanyApplied("");

        application.setAppliedDate(java.time.LocalDate.now());

        application.setStatus("Applied");

        application.setRemarks("");

        jobApplicationRepository.save(application);
    }

    return saved;
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