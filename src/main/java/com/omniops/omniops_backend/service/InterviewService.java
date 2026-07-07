package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.Interview;

import java.util.List;

public interface InterviewService {

    Interview save(Interview interview);

    List<Interview> findAll();

    Interview findById(Long id);

    Interview update(Long id, Interview interview);

    void delete(Long id);

}