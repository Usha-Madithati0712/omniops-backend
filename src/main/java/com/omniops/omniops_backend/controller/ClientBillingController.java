package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.ClientBilling;
import com.omniops.omniops_backend.service.ClientBillingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/client-billing")
@CrossOrigin(origins = "*")
public class ClientBillingController {

    @Autowired
    private ClientBillingService clientBillingService;

    @GetMapping
    public List<ClientBilling> getAllBillingRecords() {
        return clientBillingService.getAllBillingRecords();
    }

    @GetMapping("/{id}")
    public ClientBilling getBillingRecordById(@PathVariable Integer id) {
        return clientBillingService.getBillingRecordById(id)
                .orElseThrow(() -> new RuntimeException("Billing record not found"));
    }

    @PostMapping
    public ClientBilling saveBillingRecord(@RequestBody ClientBilling billing) {
        return clientBillingService.saveBillingRecord(billing);
    }

    @PutMapping("/{id}")
    public ClientBilling updateBillingRecord(@PathVariable Integer id,
                                             @RequestBody ClientBilling billing) {
        return clientBillingService.updateBillingRecord(id, billing);
    }

    @DeleteMapping("/{id}")
    public void deleteBillingRecord(@PathVariable Integer id) {
        clientBillingService.deleteBillingRecord(id);
    }
}