package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.BackgroundVerification;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BackgroundVerificationRepository
        extends JpaRepository<BackgroundVerification, Long> {

    List<BackgroundVerification> findByClientClientId(Integer clientId);

    Optional<BackgroundVerification> findTopByClientClientIdOrderBySubmittedAtDesc(
            Integer clientId
    );

}