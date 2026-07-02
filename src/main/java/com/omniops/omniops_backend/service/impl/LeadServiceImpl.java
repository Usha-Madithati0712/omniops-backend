package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.dto.LeadDashboardDTO;
import com.omniops.omniops_backend.entity.Lead;
import com.omniops.omniops_backend.repository.LeadRepository;
import com.omniops.omniops_backend.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeadServiceImpl implements LeadService {

    private final LeadRepository leadRepository;

    @Override
    public Lead saveLead(Lead lead) {
        return leadRepository.save(lead);
    }

    @Override
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    @Override
    public Lead getLead(Integer id) {
        return leadRepository.findById(id).orElse(null);
    }
@Override
public Lead updateLead(Integer id, Lead lead) {

    Lead existing = leadRepository.findById(id).orElse(null);

    if(existing == null){
        return null;
    }

    existing.setCompanyName(lead.getCompanyName());
    existing.setContactPerson(lead.getContactPerson());
    existing.setEmail(lead.getEmail());
    existing.setPhone(lead.getPhone());
    existing.setIndustry(lead.getIndustry());
    existing.setSource(lead.getSource());
    existing.setAssignedTo(lead.getAssignedTo());
    existing.setLeadStatus(lead.getLeadStatus());
    existing.setRemarks(lead.getRemarks());

    return leadRepository.save(existing);
}
    @Override
    public void deleteLead(Integer id) {
        leadRepository.deleteById(id);
    }

    @Override
    public LeadDashboardDTO getDashboard() {

        return LeadDashboardDTO.builder()
                .totalLeads(leadRepository.count())
                .newLeads(leadRepository.countByLeadStatus("New"))
                .interestedLeads(leadRepository.countByLeadStatus("Interested"))
                .convertedLeads(leadRepository.countByLeadStatus("Converted"))
                .build();
    }
}