package com.omniops.omniops_backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "interviews")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Interview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String clientName;

    private String companyName;

    private String roleName;

    private String recruiterName;

    private String interviewDate;

    private String interviewTime;

    private String mode;

    private String round;

    private String interviewType;

    private String meetingLink;

    private String status;

    private String remarks;

}