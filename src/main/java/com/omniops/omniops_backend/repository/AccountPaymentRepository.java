package com.omniops.omniops_backend.repository;

import com.omniops.omniops_backend.entity.AccountPayment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountPaymentRepository extends JpaRepository<AccountPayment, Integer> {

}