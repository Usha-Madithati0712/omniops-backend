package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.RecruitmentAssignment;
import com.omniops.omniops_backend.service.RecruitmentAssignmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/recruitment")
@RequiredArgsConstructor
@CrossOrigin("*")
public class RecruitmentAssignmentController {

    private final RecruitmentAssignmentService recruitmentAssignmentService;

    @PostMapping
    public RecruitmentAssignment save(
            @RequestBody RecruitmentAssignment assignment
    ) {
        return recruitmentAssignmentService.save(assignment);
    }

    @GetMapping
    public List<RecruitmentAssignment> all() {
        return recruitmentAssignmentService.findAll();
    }

    @GetMapping("/{id}")
    public RecruitmentAssignment get(
            @PathVariable Long id
    ) {
        return recruitmentAssignmentService.findById(id);
    }

    @PutMapping("/{id}")
    public RecruitmentAssignment update(
            @PathVariable Long id,
            @RequestBody RecruitmentAssignment assignment
    ) {
        return recruitmentAssignmentService.update(id, assignment);
    }

    @DeleteMapping("/{id}")
    public void delete(
            @PathVariable Long id
    ) {
        recruitmentAssignmentService.delete(id);
    }

}