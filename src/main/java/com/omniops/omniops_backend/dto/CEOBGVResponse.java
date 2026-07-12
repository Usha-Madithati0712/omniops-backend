package com.omniops.omniops_backend.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CEOBGVResponse {

    private Long bgvId;

    private Integer clientId;

    private String clientName;

    private String candidateName;

    private String recruiter;

    private String status;

    private LocalDateTime submittedAt;

}