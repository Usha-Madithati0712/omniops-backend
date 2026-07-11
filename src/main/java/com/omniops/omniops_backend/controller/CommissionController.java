package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.Commission;
import com.omniops.omniops_backend.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/commissions")
@CrossOrigin(origins = "*")
public class CommissionController {

    @Autowired
    private CommissionService commissionService;

    @GetMapping
    public List<Commission> getAllCommissions() {
        return commissionService.getAllCommissions();
    }

    @GetMapping("/{id}")
    public Commission getCommissionById(@PathVariable Integer id) {
        return commissionService.getCommissionById(id)
                .orElseThrow(() -> new RuntimeException("Commission not found"));
    }

    @PostMapping
    public Commission saveCommission(@RequestBody Commission commission) {
        return commissionService.saveCommission(commission);
    }

    @PutMapping("/{id}")
    public Commission updateCommission(@PathVariable Integer id,
                                       @RequestBody Commission commission) {
        return commissionService.updateCommission(id, commission);
    }

    @DeleteMapping("/{id}")
    public void deleteCommission(@PathVariable Integer id) {
        commissionService.deleteCommission(id);
    }
}