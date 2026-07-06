package com.omniops.omniops_backend.service.impl;
import com.omniops.omniops_backend.entity.Enrollment;
import com.omniops.omniops_backend.repository.EnrollmentRepository;

import java.time.LocalDate;
import java.math.BigDecimal;
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
private final EnrollmentRepository enrollmentRepository;
  @Override
public Payment savePayment(Payment payment) {

    Payment savedPayment = paymentRepository.save(payment);

    if ("Paid".equalsIgnoreCase(savedPayment.getPaymentStatus())) {

        boolean exists = enrollmentRepository.existsByCandidateName(
                savedPayment.getCandidateName()
        );

        if (!exists) {

            Enrollment enrollment = new Enrollment();

            enrollment.setCandidateName(savedPayment.getCandidateName());
            enrollment.setTechnology(savedPayment.getTechnology());
            enrollment.setRecruiterName(savedPayment.getRecruiterName());
            enrollment.setAmountPaid(savedPayment.getAmount());
           enrollment.setEnrollmentDate(savedPayment.getPaymentDate());

            enrollment.setTrainerName("");

            // IMPORTANT:
            // If your Enrollment entity has batchNumber:
            enrollment.setBatchNumber("Session 1");

            // If your Enrollment entity has session instead,
            // replace the above line with:
            // enrollment.setSession("Session 1");

            enrollment.setMode("Online");
            enrollment.setDuration("");
            enrollment.setCurrentStatus("Enrolled");
            enrollment.setRemarks("");

            enrollmentRepository.save(enrollment);
        }
    }

    return savedPayment;
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

       Payment updatedPayment = paymentRepository.save(existing);

if ("Paid".equalsIgnoreCase(updatedPayment.getPaymentStatus())) {

    boolean exists = enrollmentRepository.existsByCandidateName(
            updatedPayment.getCandidateName()
    );

    if (!exists) {

        Enrollment enrollment = new Enrollment();

        enrollment.setCandidateName(
                updatedPayment.getCandidateName()
        );

        enrollment.setTechnology(
                updatedPayment.getTechnology()
        );

        enrollment.setRecruiterName(
                updatedPayment.getRecruiterName()
        );

        enrollment.setAmountPaid(
                updatedPayment.getAmount()
        );

        enrollment.setEnrollmentDate(
                updatedPayment.getPaymentDate()
        );

        enrollment.setTrainerName("");

        enrollment.setBatchNumber("Session 1");

        enrollment.setMode("Online");

        enrollment.setDuration("");

        enrollment.setCurrentStatus("Enrolled");

        enrollment.setRemarks("");

        enrollmentRepository.save(enrollment);
    }
}

return updatedPayment;
    }

    @Override
    public void deletePayment(Long id) {

        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Payment not found"));

        paymentRepository.delete(payment);
    }

}