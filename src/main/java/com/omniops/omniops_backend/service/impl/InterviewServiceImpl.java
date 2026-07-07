package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.Interview;
import com.omniops.omniops_backend.repository.InterviewRepository;
import com.omniops.omniops_backend.service.InterviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InterviewServiceImpl implements InterviewService {

    private final InterviewRepository repository;

    @Override
    public Interview save(Interview interview) {
        return repository.save(interview);
    }

    @Override
    public List<Interview> findAll() {
        return repository.findAll();
    }

    @Override
    public Interview findById(Long id) {
        return repository.findById(id).orElse(null);
    }

    @Override
    public Interview update(Long id, Interview interview) {

        Interview existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        existing.setClientName(interview.getClientName());
        existing.setCompanyName(interview.getCompanyName());
        existing.setRoleName(interview.getRoleName());
        existing.setRecruiterName(interview.getRecruiterName());
        existing.setInterviewDate(interview.getInterviewDate());
        existing.setInterviewTime(interview.getInterviewTime());
        existing.setMode(interview.getMode());
        existing.setRound(interview.getRound());
        existing.setInterviewType(interview.getInterviewType());
        existing.setMeetingLink(interview.getMeetingLink());
        existing.setStatus(interview.getStatus());
        existing.setRemarks(interview.getRemarks());

        return repository.save(existing);
    }

    @Override
    public void delete(Long id) {

        Interview existing = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Interview not found"));

        repository.delete(existing);
    }
}