package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.dto.LeadDashboardDTO;
import com.omniops.omniops_backend.entity.Lead;

import java.util.List;

public interface LeadService {

    Lead saveLead(Lead lead);

    List<Lead> getAllLeads();

    Lead getLead(Integer id);

    Lead updateLead(Integer id, Lead lead);

    void deleteLead(Integer id);

    LeadDashboardDTO getDashboard();

}