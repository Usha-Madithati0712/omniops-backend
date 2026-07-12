package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.ClientBilling;
import com.omniops.omniops_backend.repository.ClientBillingRepository;
import com.omniops.omniops_backend.service.ClientBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientBillingServiceImpl implements ClientBillingService {

    @Autowired
    private ClientBillingRepository clientBillingRepository;

    @Override
    public List<ClientBilling> getAllBillingRecords() {
        return clientBillingRepository.findAll();
    }

    @Override
    public Optional<ClientBilling> getBillingRecordById(Integer id) {
        return clientBillingRepository.findById(id);
    }

    @Override
    public ClientBilling saveBillingRecord(ClientBilling billing) {
        return clientBillingRepository.save(billing);
    }

    @Override
    public ClientBilling updateBillingRecord(Integer id, ClientBilling billing) {

        ClientBilling existing = clientBillingRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Billing record not found"));

        existing.setClientName(billing.getClientName());
        existing.setServiceType(billing.getServiceType());
        existing.setTotalAmount(billing.getTotalAmount());
        existing.setPaidAmount(billing.getPaidAmount());
        existing.setBalanceAmount(billing.getBalanceAmount());
        existing.setDueDate(billing.getDueDate());
        existing.setBillingStatus(billing.getBillingStatus());
        existing.setReferralAmount(billing.getReferralAmount());
        existing.setRemarks(billing.getRemarks());

        return clientBillingRepository.save(existing);
    }

    @Override
    public void deleteBillingRecord(Integer id) {
        clientBillingRepository.deleteById(id);
    }
}