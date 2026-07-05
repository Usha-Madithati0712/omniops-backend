package com.omniops.omniops_backend.controller;

import com.omniops.omniops_backend.entity.Payment;
import com.omniops.omniops_backend.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.omniops.omniops_backend.service.storage.FileStorageService;

import org.springframework.http.MediaType;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaTypeFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

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

        @ModelAttribute Payment payment,

        @RequestParam(value = "paymentProof", required = false)
        MultipartFile paymentProof,

        @RequestParam(value = "invoicePdf", required = false)
        MultipartFile invoicePdf

) throws IOException {

    if (paymentProof != null && !paymentProof.isEmpty()) {

        String proof =
                fileStorageService.saveFile(
                        paymentProof,
                        "payment-proofs"
                );

        payment.setPaymentProofPath(proof);

    }

    if (invoicePdf != null && !invoicePdf.isEmpty()) {

        String invoice =
                fileStorageService.saveFile(
                        invoicePdf,
                        "invoices"
                );

        payment.setInvoicePdfPath(invoice);

    }

    Payment saved =
            paymentService.savePayment(payment);

    return ResponseEntity.ok(saved);

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
@GetMapping("/proof/{id}")
public ResponseEntity<Resource> viewProof(
        @PathVariable Long id) throws IOException {

    Payment payment = paymentService
            .getPaymentById(id)
            .orElseThrow(() ->
                    new RuntimeException("Payment Not Found"));

    if (payment.getPaymentProofPath() == null) {
        return ResponseEntity.notFound().build();
    }

    Resource resource = fileStorageService.loadFile(
            "payment-proofs",
            payment.getPaymentProofPath()
    );

    String contentType = MediaTypeFactory
            .getMediaType(resource)
            .orElse(MediaType.APPLICATION_OCTET_STREAM)
            .toString();

    return ResponseEntity.ok()
            .header(HttpHeaders.CONTENT_TYPE, contentType)
            .body(resource);
}
@GetMapping("/invoice/{id}")
public ResponseEntity<Resource> viewInvoice(
        @PathVariable Long id) throws IOException {

    Payment payment = paymentService
            .getPaymentById(id)
            .orElseThrow(() ->
                    new RuntimeException("Payment Not Found"));

    if (payment.getInvoicePdfPath() == null) {
        return ResponseEntity.notFound().build();
    }

    Resource resource = fileStorageService.loadFile(
            "invoices",
            payment.getInvoicePdfPath()
    );

    return ResponseEntity.ok()
            .header(
                    HttpHeaders.CONTENT_DISPOSITION,
                    "inline; filename=\"" +
                            payment.getInvoicePdfPath() +
                            "\""
            )
            .header(
                    HttpHeaders.CONTENT_TYPE,
                    "application/pdf"
            )
            .body(resource);

}
}