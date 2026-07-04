package com.omniops.omniops_backend.service.impl;
import com.omniops.omniops_backend.entity.BoxA;
import com.omniops.omniops_backend.repository.BoxARepository;
import com.omniops.omniops_backend.service.BoxAService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BoxAServiceImpl implements BoxAService {

    private final BoxARepository boxARepository;

    @Override
    public BoxA save(BoxA boxA) {
        return boxARepository.save(boxA);
    }

    @Override
    public List<BoxA> findAll() {
        return boxARepository.findAll();
    }

    @Override
    public BoxA findById(Integer id) {
        return boxARepository.findById(id).orElse(null);
    }

    @Override
    public BoxA update(Integer id, BoxA boxA) {

        BoxA existing = boxARepository.findById(id).orElse(null);

        if (existing == null) {
            return null;
        }

        existing.setFullName(boxA.getFullName());
        existing.setPhone(boxA.getPhone());
        existing.setEmail(boxA.getEmail());
        existing.setLocation(boxA.getLocation());
        existing.setCompany(boxA.getCompany());
        existing.setExperience(boxA.getExperience());
        existing.setResumeFile(boxA.getResumeFile());
        existing.setPreviousCompany(boxA.getPreviousCompany());
        existing.setYearsWorked(boxA.getYearsWorked());
        existing.setDesignation(boxA.getDesignation());
        existing.setQualification(boxA.getQualification());
        existing.setCollege(boxA.getCollege());
        existing.setGraduationYear(boxA.getGraduationYear());
        existing.setTargetRole(boxA.getTargetRole());
        existing.setTechnology(boxA.getTechnology());
        existing.setExpectedCTC(boxA.getExpectedCTC());
        existing.setPreferredLocation(boxA.getPreferredLocation());
        existing.setUsername(boxA.getUsername());
        existing.setPassword(boxA.getPassword());
        existing.setAssignedRecruiter(boxA.getAssignedRecruiter());

        return boxARepository.save(existing);
    }

    @Override
    public void delete(Integer id) {
        boxARepository.deleteById(id);
    }
}