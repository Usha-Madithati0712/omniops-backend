package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.RecruitmentAssignment;

import java.util.List;

public interface RecruitmentAssignmentService {

    RecruitmentAssignment save(RecruitmentAssignment assignment);

    List<RecruitmentAssignment> findAll();

    RecruitmentAssignment findById(Long id);

    RecruitmentAssignment update(Long id, RecruitmentAssignment assignment);

    void delete(Long id);

}