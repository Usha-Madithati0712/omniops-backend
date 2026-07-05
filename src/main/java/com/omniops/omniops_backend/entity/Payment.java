package com.omniops.omniops_backend.entity;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Candidate Details
    @Column(nullable = false)
    private String candidateName;

    private String technology;

    private String recruiterName;

    // Payment Details
    @Column(nullable = false)
    private BigDecimal amount;

    private LocalDate paymentDate;

    private String paymentMethod;

    // UPI Details
    private String upiId;

    private String transactionId;

    // Bank Details
    private String bankName;

    private String chequeNumber;

    private String accountNumber;

    private String referenceNumber;

    // Invoice Details
    private String invoiceNo;

    private LocalDate invoiceDate;

    private BigDecimal gstAmount;

    private BigDecimal totalAmount;

    // Status
    private String paymentStatus;

    // File Paths
    @Column(length = 1000)
    private String paymentProofPath;

    @Column(length = 1000)
    private String invoicePdfPath;

    // Audit Fields
    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    private String createdBy;

    private String updatedBy;

    @PrePersist
    public void prePersist() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        updatedAt = LocalDateTime.now();
    }
}