package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.Client;
import com.omniops.omniops_backend.repository.ClientRepository;
import com.omniops.omniops_backend.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.omniops.omniops_backend.entity.Lead;
import com.omniops.omniops_backend.repository.LeadRepository;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final LeadRepository leadRepository;

    @Override
    public Client save(Client client) {
        return clientRepository.save(client);
    }

    @Override
    public List<Client> findAll() {
        return clientRepository.findAll();
    }

    @Override
    public Client findById(Integer id) {
        return clientRepository.findById(id).orElse(null);
    }
@Override
public Client update(Integer id, Client client) {

    Client existing = clientRepository.findById(id).orElse(null);

    if (existing == null) {
        return null;
    }

    existing.setCompanyName(client.getCompanyName());
    existing.setIndustry(client.getIndustry());
    existing.setRecruiterName(client.getRecruiterName());
    existing.setExpectedCtc(client.getExpectedCtc());
    existing.setPaymentStatus(client.getPaymentStatus());
    existing.setStatus(client.getStatus());
    existing.setClientType(client.getClientType());

    return clientRepository.save(existing);
}
    @Override
    public void delete(Integer id) {
        clientRepository.deleteById(id);
    }
    @Override
public Client convertLead(Integer leadId) {

    Lead lead = leadRepository.findById(leadId).orElse(null);

    if (lead == null) {
        return null;
    }

    Client client = new Client();

    client.setLead(lead);
    client.setCompanyName(lead.getCompanyName());
    client.setIndustry(lead.getIndustry());
   client.setRecruiterName(lead.getAssignedTo());

client.setExpectedCtc("");

client.setPaymentStatus("Pending");
    client.setClientType(Client.ClientType.Startup);
    client.setStatus(Client.Status.Active);
lead.setLeadStatus("Converted");
leadRepository.save(lead);
    return clientRepository.save(client);
}
}
