package com.omniops.omniops_backend.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class BoxARequest {

    private String fullName;
    private String phone;
    private String email;

    private String location;
    private String company;
    private String experience;

    private MultipartFile resume;

    private String previousCompany;
    private String yearsWorked;
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

}