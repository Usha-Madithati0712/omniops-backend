package com.omniops.omniops_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "enrollments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Candidate Details
    private String candidateName;

    private String technology;

    private String recruiterName;

    private BigDecimal amountPaid;

    private LocalDate enrollmentDate;

    // Training Details
    private String trainerName;

    private String batchNumber;

    private String mode;

    private String duration;

    // Status
    private String currentStatus;

    @Column(length = 1000)
    private String remarks;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;
}