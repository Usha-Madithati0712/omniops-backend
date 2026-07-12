package com.omniops.omniops_backend.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "internal_hiring")
public class InternalHiring {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String candidateName;
    private String appliedPosition;
    private String source;
    private String recruiter;

    private String status;

    private String hrRound;
    private String technicalRound;
    private String managerRound;
    private String finalRound;

    private String offerDate;
    private Double salaryOffered;
    private String joiningDate;
    private String offerStatus;

    private String roleAssignment;
    private String salarySetup;
    private String managerAssignment;
    private String complianceCollection;
    private String accountCreation;

    public InternalHiring() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCandidateName() {
        return candidateName;
    }

    public void setCandidateName(String candidateName) {
        this.candidateName = candidateName;
    }

    public String getAppliedPosition() {
        return appliedPosition;
    }

    public void setAppliedPosition(String appliedPosition) {
        this.appliedPosition = appliedPosition;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getRecruiter() {
        return recruiter;
    }

    public void setRecruiter(String recruiter) {
        this.recruiter = recruiter;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getHrRound() {
        return hrRound;
    }

    public void setHrRound(String hrRound) {
        this.hrRound = hrRound;
    }

    public String getTechnicalRound() {
        return technicalRound;
    }

    public void setTechnicalRound(String technicalRound) {
        this.technicalRound = technicalRound;
    }

    public String getManagerRound() {
        return managerRound;
    }

    public void setManagerRound(String managerRound) {
        this.managerRound = managerRound;
    }

    public String getFinalRound() {
        return finalRound;
    }

    public void setFinalRound(String finalRound) {
        this.finalRound = finalRound;
    }

    public String getOfferDate() {
        return offerDate;
    }

    public void setOfferDate(String offerDate) {
        this.offerDate = offerDate;
    }

    public Double getSalaryOffered() {
        return salaryOffered;
    }

    public void setSalaryOffered(Double salaryOffered) {
        this.salaryOffered = salaryOffered;
    }

    public String getJoiningDate() {
        return joiningDate;
    }

    public void setJoiningDate(String joiningDate) {
        this.joiningDate = joiningDate;
    }

    public String getOfferStatus() {
        return offerStatus;
    }

    public void setOfferStatus(String offerStatus) {
        this.offerStatus = offerStatus;
    }

    public String getRoleAssignment() {
        return roleAssignment;
    }

    public void setRoleAssignment(String roleAssignment) {
        this.roleAssignment = roleAssignment;
    }

    public String getSalarySetup() {
        return salarySetup;
    }

    public void setSalarySetup(String salarySetup) {
        this.salarySetup = salarySetup;
    }

    public String getManagerAssignment() {
        return managerAssignment;
    }

    public void setManagerAssignment(String managerAssignment) {
        this.managerAssignment = managerAssignment;
    }

    public String getComplianceCollection() {
        return complianceCollection;
    }

    public void setComplianceCollection(String complianceCollection) {
        this.complianceCollection = complianceCollection;
    }

    public String getAccountCreation() {
        return accountCreation;
    }

    public void setAccountCreation(String accountCreation) {
        this.accountCreation = accountCreation;
    }
}