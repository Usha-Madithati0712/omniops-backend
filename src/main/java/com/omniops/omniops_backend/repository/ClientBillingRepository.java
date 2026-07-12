package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.ClientBilling;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientBillingRepository extends JpaRepository<ClientBilling, Integer> {

}