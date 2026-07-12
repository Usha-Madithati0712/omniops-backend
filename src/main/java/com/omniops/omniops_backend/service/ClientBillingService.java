package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.ClientBilling;

import java.util.List;
import java.util.Optional;

public interface ClientBillingService {

    List<ClientBilling> getAllBillingRecords();

    Optional<ClientBilling> getBillingRecordById(Integer id);

    ClientBilling saveBillingRecord(ClientBilling billing);

    ClientBilling updateBillingRecord(Integer id, ClientBilling billing);

    void deleteBillingRecord(Integer id);

}