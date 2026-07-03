package com.omniops.omniops_backend.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "clients")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "client_id")
    private Integer clientId;
@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "lead_id")
@JsonIgnore
private Lead lead;
    @Column(name = "client_code")
    private String clientCode;

    @Column(name = "company_name")
    private String companyName;

    @Enumerated(EnumType.STRING)
    @Column(name = "client_type")
    private ClientType clientType;

    @Column(name = "industry")
    private String industry;
@Column(name = "recruiter_name")
private String recruiterName;

@Column(name = "expected_ctc")
private String expectedCtc;

@Column(name = "payment_status")
private String paymentStatus;
    @Column(name = "website")
    private String website;

    @Column(name = "gst_number")
    private String gstNumber;

    @Column(name = "billing_address")
    private String billingAddress;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_manager")
    private User accountManager;

    @Column(name = "created_at", insertable = false, updatable = false)
    private LocalDateTime createdAt;

    public enum ClientType {
        Startup,
        SME,
        Enterprise
    }

   public enum Status {

    Active,

    Paused,

    Stopped,

    Placed,

    Non_Priority

}
}