package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.Commission;

import java.util.List;
import java.util.Optional;

public interface CommissionService {

    List<Commission> getAllCommissions();

    Optional<Commission> getCommissionById(Integer id);

    Commission saveCommission(Commission commission);

    Commission updateCommission(Integer id, Commission commission);

    void deleteCommission(Integer id);
}