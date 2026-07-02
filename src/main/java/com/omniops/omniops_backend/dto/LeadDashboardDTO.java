package com.omniops.omniops_backend.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LeadDashboardDTO {

    private long totalLeads;

    private long newLeads;

    private long interestedLeads;

    private long convertedLeads;

}
