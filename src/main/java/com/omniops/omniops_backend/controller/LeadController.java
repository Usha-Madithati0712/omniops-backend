package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.Lead;
import com.omniops.omniops_backend.service.LeadService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import com.omniops.omniops_backend.dto.LeadDashboardDTO;
import java.util.List;

@RestController
@RequestMapping("/api/leads")
@RequiredArgsConstructor
public class LeadController {

    private final LeadService leadService;

    @PostMapping
    public Lead save(@RequestBody Lead lead) {
        return leadService.saveLead(lead);
    }

    @GetMapping
    public List<Lead> all() {
        return leadService.getAllLeads();
    }

    @GetMapping("/{id}")
    public Lead get(@PathVariable Integer id) {
        return leadService.getLead(id);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Integer id) {
        leadService.deleteLead(id);
    }
    @GetMapping("/dashboard")
public LeadDashboardDTO dashboard() {

    return leadService.getDashboard();

}
}