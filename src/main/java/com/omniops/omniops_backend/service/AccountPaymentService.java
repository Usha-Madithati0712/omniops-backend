package com.omniops.omniops_backend.service;

import com.omniops.omniops_backend.entity.AccountPayment;

import java.util.List;
import java.util.Optional;

public interface AccountPaymentService {

    List<AccountPayment> getAllPayments();

    Optional<AccountPayment> getPaymentById(Integer id);

    AccountPayment savePayment(AccountPayment payment);

    AccountPayment updatePayment(Integer id, AccountPayment payment);

    void deletePayment(Integer id);

}