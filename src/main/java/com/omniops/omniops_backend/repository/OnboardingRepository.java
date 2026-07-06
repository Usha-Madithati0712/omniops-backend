package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.Onboarding;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OnboardingRepository extends JpaRepository<Onboarding, Long> {

    boolean existsByClientName(String clientName);

}