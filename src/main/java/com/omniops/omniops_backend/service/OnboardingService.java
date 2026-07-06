package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.Onboarding;

import java.util.List;
import java.util.Optional;

public interface OnboardingService {

    Onboarding saveOnboarding(Onboarding onboarding);

    List<Onboarding> getAllOnboardings();

    Optional<Onboarding> getOnboardingById(Long id);

    Onboarding updateOnboarding(Long id, Onboarding onboarding);

    void deleteOnboarding(Long id);

}