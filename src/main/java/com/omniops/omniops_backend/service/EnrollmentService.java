package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.Enrollment;

import java.util.List;

public interface EnrollmentService {

    Enrollment save(Enrollment enrollment);

    List<Enrollment> findAll();

    Enrollment findById(Long id);

    Enrollment update(Long id, Enrollment enrollment);

    void delete(Long id);

}