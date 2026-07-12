package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.AccountPayment;
import com.omniops.omniops_backend.repository.AccountPaymentRepository;
import com.omniops.omniops_backend.service.AccountPaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AccountPaymentServiceImpl implements AccountPaymentService {

    @Autowired
    private AccountPaymentRepository accountPaymentRepository;

    @Override
    public List<AccountPayment> getAllPayments() {
        return accountPaymentRepository.findAll();
    }

    @Override
    public Optional<AccountPayment> getPaymentById(Integer id) {
        return accountPaymentRepository.findById(id);
    }

    @Override
    public AccountPayment savePayment(AccountPayment payment) {
        return accountPaymentRepository.save(payment);
    }

    @Override
    public AccountPayment updatePayment(Integer id, AccountPayment payment) {

        AccountPayment existing = accountPaymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        existing.setClientName(payment.getClientName());
        existing.setPaymentId(payment.getPaymentId());
        existing.setPaymentDate(payment.getPaymentDate());
        existing.setAmountPaid(payment.getAmountPaid());
        existing.setPaymentMethod(payment.getPaymentMethod());
        existing.setInstallmentNo(payment.getInstallmentNo());
        existing.setPaymentStatus(payment.getPaymentStatus());
        existing.setTotalInstallments(payment.getTotalInstallments());
        existing.setCurrentInstallment(payment.getCurrentInstallment());
        existing.setRefundAmount(payment.getRefundAmount());
        existing.setRefundStatus(payment.getRefundStatus());
        existing.setPaymentHistory(payment.getPaymentHistory());

        return accountPaymentRepository.save(existing);
    }

    @Override
    public void deletePayment(Integer id) {
        accountPaymentRepository.deleteById(id);
    }
}