package com.omniops.omniops_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "background_verification")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BackgroundVerification {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bgvId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_id")
    private Client client;

    // ======================
    // Personal Details
    // ======================

    private String fullName;

    private LocalDate dob;

    private String phoneNumber;

    private String alternatePhone;

    private String fatherName;

    private String motherName;

    // ======================
    // Email Details
    // ======================

    private String marketingEmail;

    private String marketingPassword;

    private String nonMarketingEmail;

    // ======================
    // Visa
    // ======================

    private String uscisNumber;

    private String optName;

    private LocalDate optStart;

    private LocalDate optEnd;

    // ======================
    // SSN
    // ======================

    private String ssnNumber;

    private String ssnName;

    // ======================
    // Status
    // ======================

    private String status;

    private String submittedBy;

    private LocalDateTime submittedAt;

}