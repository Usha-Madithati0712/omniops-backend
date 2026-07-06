package com.omniops.omniops_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "onboarding")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Onboarding {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Client Details
    private String clientName;

    private String technology;

    private String paymentPlan;

    private String contractNumber;

    // Contract Details
    private LocalDate generatedDate;

    private String contractStatus;

    private String signedStatus;

    // Client Status
    private String clientStatus;

    // Queue Status
    private String queueStatus;

    @Column(length = 1000)
    private String remarks;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;
}