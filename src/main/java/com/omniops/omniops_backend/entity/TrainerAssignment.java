package com.omniops.omniops_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "trainer_assignments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class TrainerAssignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String candidateName;

    private String recruiterName;

    private String companyName;

    private String roleName;

    private String trainerName;

    private String technology;

    private String batchNumber;

    private String startDate;

    private String endDate;

    private String duration;

    private String mode;

    private String status;

    @Column(length = 2000)
    private String remarks;

}