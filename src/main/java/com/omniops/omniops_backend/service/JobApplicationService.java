package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.JobApplication;

import java.util.List;

public interface JobApplicationService {

    JobApplication save(JobApplication jobApplication);

    List<JobApplication> findAll();

    JobApplication findById(Long id);

    JobApplication update(Long id, JobApplication jobApplication);

    void delete(Long id);

}