package com.omniops.omniops_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "job_applications")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JobApplication {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Candidate Details
    private String clientName;

    private String recruiterName;

    // Job Details
    private String roleApplied;

    private String companyApplied;

    private LocalDate appliedDate;

    // Application Status
    private String status;

    @Column(length = 1000)
    private String remarks;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;

}