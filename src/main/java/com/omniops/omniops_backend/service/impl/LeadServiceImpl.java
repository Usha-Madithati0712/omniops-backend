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
public Lead updateLead(Integer id, Lead updatedLead) {

    Lead existingLead = leadRepository.findById(id).orElse(null);

    if(existingLead == null){
        return null;
    }

    existingLead.setCompanyName(updatedLead.getCompanyName());
    existingLead.setLeadStatus(updatedLead.getLeadStatus());
    existingLead.setAssignedTo(updatedLead.getAssignedTo());
    existingLead.setIndustry(updatedLead.getIndustry());
    existingLead.setRemarks(updatedLead.getRemarks());
    existingLead.setSource(updatedLead.getSource());

    return leadRepository.save(existingLead);

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