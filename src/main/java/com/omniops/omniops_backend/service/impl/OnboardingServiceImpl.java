package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.Onboarding;
import com.omniops.omniops_backend.repository.OnboardingRepository;
import com.omniops.omniops_backend.service.OnboardingService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class OnboardingServiceImpl implements OnboardingService {

    private final OnboardingRepository onboardingRepository;

    @Override
    public Onboarding saveOnboarding(Onboarding onboarding) {
        return onboardingRepository.save(onboarding);
    }

    @Override
    public List<Onboarding> getAllOnboardings() {
        return onboardingRepository.findAll();
    }

    @Override
    public Optional<Onboarding> getOnboardingById(Long id) {
        return onboardingRepository.findById(id);
    }

    @Override
    public Onboarding updateOnboarding(Long id, Onboarding onboarding) {

        Onboarding existing = onboardingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Onboarding record not found"));

        existing.setClientName(onboarding.getClientName());
        existing.setTechnology(onboarding.getTechnology());
        existing.setPaymentPlan(onboarding.getPaymentPlan());
        existing.setContractNumber(onboarding.getContractNumber());

        existing.setGeneratedDate(onboarding.getGeneratedDate());
        existing.setContractStatus(onboarding.getContractStatus());
        existing.setSignedStatus(onboarding.getSignedStatus());

        existing.setClientStatus(onboarding.getClientStatus());
        existing.setQueueStatus(onboarding.getQueueStatus());

        existing.setRemarks(onboarding.getRemarks());

        return onboardingRepository.save(existing);
    }

    @Override
    public void deleteOnboarding(Long id) {

        Onboarding onboarding = onboardingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Onboarding record not found"));

        onboardingRepository.delete(onboarding);
    }
}