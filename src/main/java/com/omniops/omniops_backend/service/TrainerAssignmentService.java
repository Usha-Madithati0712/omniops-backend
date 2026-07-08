package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.TrainerAssignment;

import java.util.List;

public interface TrainerAssignmentService {

    TrainerAssignment save(TrainerAssignment trainerAssignment);

    List<TrainerAssignment> findAll();

    TrainerAssignment findById(Long id);

    TrainerAssignment update(Long id, TrainerAssignment trainerAssignment);

    void delete(Long id);

}