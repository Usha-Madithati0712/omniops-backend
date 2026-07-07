package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.JobApplication;
import com.omniops.omniops_backend.repository.JobApplicationRepository;
import com.omniops.omniops_backend.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

    private final JobApplicationRepository repository;

    @Override
    public JobApplication save(JobApplication jobApplication) {

        if (jobApplication.getStatus() == null ||
                jobApplication.getStatus().trim().isEmpty()) {

            jobApplication.setStatus("Applied");
        }

        return repository.save(jobApplication);
    }

    @Override
    public List<JobApplication> findAll() {
        return repository.findAll();
    }

    @Override
    public JobApplication findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public JobApplication update(Long id, JobApplication jobApplication) {

        JobApplication existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Application not found"));

        existing.setClientName(jobApplication.getClientName());
        existing.setRecruiterName(jobApplication.getRecruiterName());
        existing.setRoleApplied(jobApplication.getRoleApplied());
        existing.setCompanyApplied(jobApplication.getCompanyApplied());
        existing.setAppliedDate(jobApplication.getAppliedDate());
        existing.setStatus(jobApplication.getStatus());
        existing.setRemarks(jobApplication.getRemarks());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {

        JobApplication existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Application not found"));

        repository.delete(existing);
    }
}