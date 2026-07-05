package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.Payment;
import com.omniops.omniops_backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.omniops.omniops_backend.service.storage.FileStorageService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class PaymentController {

    private final PaymentService paymentService;
private final FileStorageService fileStorageService;
    // CREATE PAYMENT
    @PostMapping
    public ResponseEntity<Payment> createPayment(
            @RequestBody Payment payment) {

        return ResponseEntity.ok(
                paymentService.savePayment(payment)
        );
    }
@PostMapping(
        value = "/upload",
        consumes = MediaType.MULTIPART_FORM_DATA_VALUE
)
public ResponseEntity<?> uploadPayment(

        @RequestParam String candidateName,
        @RequestParam String technology,
        @RequestParam String recruiterName,

        @RequestParam Double amount,
        @RequestParam String paymentDate,
        @RequestParam String paymentMethod,

        @RequestParam(required = false) String upiId,
        @RequestParam(required = false) String transactionId,

        @RequestParam(required = false) String bankName,
        @RequestParam(required = false) String chequeNumber,
        @RequestParam(required = false) String accountNumber,
        @RequestParam(required = false) String referenceNumber,

        @RequestParam(required = false) String invoiceNo,
        @RequestParam(required = false) String invoiceDate,

        @RequestParam(required = false) Double gstAmount,
        @RequestParam(required = false) Double totalAmount,

        @RequestParam String paymentStatus,

        @RequestParam(required = false) MultipartFile paymentProof,
        @RequestParam(required = false) MultipartFile invoicePdf

) throws IOException {

    Payment payment = new Payment();

    payment.setCandidateName(candidateName);
    payment.setTechnology(technology);
    payment.setRecruiterName(recruiterName);

    payment.setAmount(java.math.BigDecimal.valueOf(amount));

    payment.setPaymentDate(java.time.LocalDate.parse(paymentDate));

    payment.setPaymentMethod(paymentMethod);

    payment.setUpiId(upiId);
    payment.setTransactionId(transactionId);

    payment.setBankName(bankName);
    payment.setChequeNumber(chequeNumber);
    payment.setAccountNumber(accountNumber);
    payment.setReferenceNumber(referenceNumber);

    payment.setInvoiceNo(invoiceNo);

    if(invoiceDate != null && !invoiceDate.isBlank()){
        payment.setInvoiceDate(
                java.time.LocalDate.parse(invoiceDate)
        );
    }

    payment.setGstAmount(
            java.math.BigDecimal.valueOf(
                    gstAmount == null ? 0 : gstAmount
            )
    );

    payment.setTotalAmount(
            java.math.BigDecimal.valueOf(
                    totalAmount == null ? 0 : totalAmount
            )
    );

    payment.setPaymentStatus(paymentStatus);

    String proofPath =
            fileStorageService.saveFile(
                    paymentProof,
                    "payment-proofs"
            );

    String invoicePath =
            fileStorageService.saveFile(
                    invoicePdf,
                    "invoices"
            );

    payment.setPaymentProofPath(proofPath);
    payment.setInvoicePdfPath(invoicePath);

    return ResponseEntity.ok(
            paymentService.savePayment(payment)
    );

}
    // GET ALL PAYMENTS
    @GetMapping
    public ResponseEntity<List<Payment>> getAllPayments() {

        return ResponseEntity.ok(
                paymentService.getAllPayments()
        );
    }

    // GET PAYMENT BY ID
    @GetMapping("/{id}")
    public ResponseEntity<Payment> getPaymentById(
            @PathVariable Long id) {

        return paymentService.getPaymentById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // UPDATE PAYMENT
    @PutMapping("/{id}")
    public ResponseEntity<Payment> updatePayment(
            @PathVariable Long id,
            @RequestBody Payment payment) {

        return ResponseEntity.ok(
                paymentService.updatePayment(id, payment)
        );
    }

    // DELETE PAYMENT
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePayment(
            @PathVariable Long id) {

        paymentService.deletePayment(id);

        return ResponseEntity.ok("Payment Deleted Successfully");
    }

}