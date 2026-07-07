package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.JobApplication;
import com.omniops.omniops_backend.service.JobApplicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/job-applications")
@RequiredArgsConstructor
@CrossOrigin("*")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @PostMapping
    public JobApplication save(
            @RequestBody JobApplication jobApplication) {

        return jobApplicationService.save(jobApplication);
    }

    @GetMapping
    public List<JobApplication> all() {

        return jobApplicationService.findAll();
    }

    @GetMapping("/{id}")
    public JobApplication get(
            @PathVariable Long id) {

        return jobApplicationService.findById(id);
    }

    @PutMapping("/{id}")
    public JobApplication update(
            @PathVariable Long id,
            @RequestBody JobApplication jobApplication) {

        return jobApplicationService.update(id, jobApplication);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id) {

        jobApplicationService.delete(id);
    }
}