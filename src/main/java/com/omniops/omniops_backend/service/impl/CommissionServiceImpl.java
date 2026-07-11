package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.Commission;
import com.omniops.omniops_backend.repository.CommissionRepository;
import com.omniops.omniops_backend.service.CommissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CommissionServiceImpl implements CommissionService {

    @Autowired
    private CommissionRepository commissionRepository;

    @Override
    public List<Commission> getAllCommissions() {
        return commissionRepository.findAll();
    }

    @Override
    public Optional<Commission> getCommissionById(Integer id) {
        return commissionRepository.findById(id);
    }

    @Override
    public Commission saveCommission(Commission commission) {
        return commissionRepository.save(commission);
    }

    @Override
    public Commission updateCommission(Integer id, Commission commission) {

        Commission existingCommission = commissionRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Commission not found"));

        existingCommission.setEmployeeName(commission.getEmployeeName());
        existingCommission.setDepartment(commission.getDepartment());
        existingCommission.setClientName(commission.getClientName());
        existingCommission.setPlacementCount(commission.getPlacementCount());
        existingCommission.setIncentiveType(commission.getIncentiveType());
        existingCommission.setIncentiveAmount(commission.getIncentiveAmount());
        existingCommission.setBonusAmount(commission.getBonusAmount());
        existingCommission.setCommissionAmount(commission.getCommissionAmount());
        existingCommission.setPaymentStatus(commission.getPaymentStatus());
        existingCommission.setRemarks(commission.getRemarks());

        return commissionRepository.save(existingCommission);
    }

    @Override
    public void deleteCommission(Integer id) {
        commissionRepository.deleteById(id);
    }
}