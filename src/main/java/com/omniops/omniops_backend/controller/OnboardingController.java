package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.Onboarding;
import com.omniops.omniops_backend.service.OnboardingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/onboarding")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OnboardingController {

    private final OnboardingService onboardingService;

    // CREATE
    @PostMapping
    public ResponseEntity<Onboarding> createOnboarding(
            @RequestBody Onboarding onboarding) {

        return ResponseEntity.ok(
                onboardingService.saveOnboarding(onboarding)
        );
    }

    // GET ALL
    @GetMapping
    public ResponseEntity<List<Onboarding>> getAllOnboardings() {

        return ResponseEntity.ok(
                onboardingService.getAllOnboardings()
        );
    }

    // GET BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Onboarding> getOnboardingById(
            @PathVariable Long id) {

        return onboardingService.getOnboardingById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<Onboarding> updateOnboarding(
            @PathVariable Long id,
            @RequestBody Onboarding onboarding) {

        return ResponseEntity.ok(
                onboardingService.updateOnboarding(id, onboarding)
        );
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOnboarding(
            @PathVariable Long id) {

        onboardingService.deleteOnboarding(id);

        return ResponseEntity.ok("Onboarding Deleted Successfully");
    }
}