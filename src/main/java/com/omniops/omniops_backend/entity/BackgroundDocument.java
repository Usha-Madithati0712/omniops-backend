package com.omniops.omniops_backend.entity;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "background_documents")
public class BackgroundDocument {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Candidate Relationship
   @Column(nullable = false)
private Integer clientId;

@Column(nullable = false)
private String clientName;
@Column(nullable = false)
private Long bgvId;
public Long getBgvId() {
    return bgvId;
}

public void setBgvId(Long bgvId) {
    this.bgvId = bgvId;
}
    // Resume, Passport, Aadhaar, Degree...
    @Column(nullable = false)
    private String documentType;

    // Original uploaded filename
    @Column(nullable = false)
    private String originalFileName;

    // File stored on Railway
    @Column(nullable = false, unique = true)
    private String storedFileName;

    // Download URL
    @Column(nullable = false, length = 1000)
    private String fileUrl;

    // Uploaded by recruiter
    private String uploadedBy;

    // Upload Time
    private LocalDateTime uploadedAt;

    public BackgroundDocument() {
    }

    public Long getId() {
        return id;
    }
    public Integer getClientId() {
    return clientId;
}

public void setClientId(Integer clientId) {
    this.clientId = clientId;
}

public String getClientName() {
    return clientName;
}

public void setClientName(String clientName) {
    this.clientName = clientName;
}
    public String getDocumentType() {
        return documentType;
    }

    public void setDocumentType(String documentType) {
        this.documentType = documentType;
    }

    public String getOriginalFileName() {
        return originalFileName;
    }

    public void setOriginalFileName(String originalFileName) {
        this.originalFileName = originalFileName;
    }

    public String getStoredFileName() {
        return storedFileName;
    }

    public void setStoredFileName(String storedFileName) {
        this.storedFileName = storedFileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getUploadedBy() {
        return uploadedBy;
    }

    public void setUploadedBy(String uploadedBy) {
        this.uploadedBy = uploadedBy;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }
}