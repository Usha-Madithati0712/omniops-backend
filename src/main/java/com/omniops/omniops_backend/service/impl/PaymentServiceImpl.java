package com.omniops.omniops_backend.service.impl;

import com.omniops.omniops_backend.entity.Payment;
import com.omniops.omniops_backend.repository.PaymentRepository;
import com.omniops.omniops_backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;

    @Override
    public Payment savePayment(Payment payment) {
        return paymentRepository.save(payment);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }

    @Override
    public Optional<Payment> getPaymentById(Long id) {
        return paymentRepository.findById(id);
    }

    @Override
    public Payment updatePayment(Long id, Payment payment) {

        Payment existing = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        existing.setCandidateName(payment.getCandidateName());
        existing.setTechnology(payment.getTechnology());
        existing.setRecruiterName(payment.getRecruiterName());

        existing.setAmount(payment.getAmount());
        existing.setPaymentDate(payment.getPaymentDate());
        existing.setPaymentMethod(payment.getPaymentMethod());

        existing.setUpiId(payment.getUpiId());
        existing.setTransactionId(payment.getTransactionId());

        existing.setBankName(payment.getBankName());
        existing.setChequeNumber(payment.getChequeNumber());
        existing.setAccountNumber(payment.getAccountNumber());
        existing.setReferenceNumber(payment.getReferenceNumber());

        existing.setInvoiceNo(payment.getInvoiceNo());
        existing.setInvoiceDate(payment.getInvoiceDate());

        existing.setGstAmount(payment.getGstAmount());
        existing.setTotalAmount(payment.getTotalAmount());

        existing.setPaymentStatus(payment.getPaymentStatus());

        existing.setPaymentProofPath(payment.getPaymentProofPath());
        existing.setInvoicePdfPath(payment.getInvoicePdfPath());

        existing.setUpdatedBy(payment.getUpdatedBy());

        return paymentRepository.save(existing);
    }

    @Override
    public void deletePayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        paymentRepository.delete(payment);
    }

}