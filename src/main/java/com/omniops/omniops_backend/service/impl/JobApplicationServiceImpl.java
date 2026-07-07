package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.JobApplication;
import com.omniops.omniops_backend.entity.Interview;

import com.omniops.omniops_backend.repository.JobApplicationRepository;
import com.omniops.omniops_backend.repository.InterviewRepository;

import com.omniops.omniops_backend.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobApplicationServiceImpl implements JobApplicationService {

   private final JobApplicationRepository repository;

private final InterviewRepository interviewRepository;

  @Override
public JobApplication save(JobApplication jobApplication) {

    if (jobApplication.getStatus() == null ||
            jobApplication.getStatus().trim().isEmpty()) {

        jobApplication.setStatus("Applied");
    }

    JobApplication saved = repository.save(jobApplication);

    if ("Interview Scheduled".equals(saved.getStatus())) {

        boolean exists =
                interviewRepository.existsByClientNameAndCompanyName(

                        saved.getClientName(),

                        saved.getCompanyApplied()

                );

        if (!exists) {

            Interview interview = new Interview();

            interview.setClientName(saved.getClientName());

            interview.setCompanyName(saved.getCompanyApplied());

            interview.setRoleName(saved.getRoleApplied());

            interview.setRecruiterName(saved.getRecruiterName());

            interview.setStatus("Scheduled");

            interviewRepository.save(interview);

        }

    }

    return saved;
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

        JobApplication updated = repository.save(existing);

if ("Interview Scheduled".equals(updated.getStatus())) {

    boolean exists =
            interviewRepository.existsByClientNameAndCompanyName(

                    updated.getClientName(),

                    updated.getCompanyApplied()

            );

    if (!exists) {

        Interview interview = new Interview();

        interview.setClientName(updated.getClientName());

        interview.setCompanyName(updated.getCompanyApplied());

        interview.setRoleName(updated.getRoleApplied());

        interview.setRecruiterName(updated.getRecruiterName());

        interview.setStatus("Scheduled");

        interviewRepository.save(interview);

    }

}

return updated;
    }

    @Override
    public void delete(Long id) {

        JobApplication existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Job Application not found"));

        repository.delete(existing);
    }
}