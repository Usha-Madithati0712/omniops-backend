package com.omniops.omniops_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "box_a")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoxA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String fullName;

    private String phone;

    private String email;

    private String location;

    private String company;

    private String experience;

    private String resumeFile;

    private String previousCompany;

    private String yearsWorked;
private String resumeOriginalName;
    private String designation;

    private String qualification;

    private String college;

    private String graduationYear;

    private String targetRole;

    private String technology;

    private String expectedCTC;

    private String preferredLocation;

    private String username;

    private String password;

    private String assignedRecruiter;

    @Column(insertable = false, updatable = false)
    private LocalDateTime createdAt;
}