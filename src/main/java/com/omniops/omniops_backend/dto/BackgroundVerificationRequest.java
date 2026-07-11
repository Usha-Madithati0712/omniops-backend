package com.omniops.omniops_backend.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BackgroundVerificationRequest {

    // Client
    private Integer clientId;

    // Personal Details
    private String fullName;
    private String dob;
    private String phoneNumber;
    private String alternatePhone;
    private String fatherName;
    private String motherName;

    // Email
    private String marketingEmail;
    private String marketingPassword;
    private String nonMarketingEmail;

    // OPT
    private String uscisNumber;
    private String optName;
    private String optStart;
    private String optEnd;

    // SSN
    private String ssnNumber;
    private String ssnName;

    // Submitted By
    private String submittedBy;

}