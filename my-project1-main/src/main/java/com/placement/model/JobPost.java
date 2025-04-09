package com.placement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

@Document(collection = "job_posts")
public class JobPost {
    @Id
    private String id;
    private String recruiterId;
    private String companyId;
    private String title;
    private String description;
    private String eligibilityCriteria;
    private double ctc;
    private String location;
    private Date deadline;
    private Date createdAt;
    private JobStatus status;

    // Enums
    public enum JobStatus {
        ACTIVE, CLOSED
    }

    // Constructors
    public JobPost() {
        this.createdAt = new Date();
        this.status = JobStatus.ACTIVE;
    }

    public JobPost(String recruiterId, String companyId, String title) {
        this();
        this.recruiterId = recruiterId;
        this.companyId = companyId;
        this.title = title;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecruiterId() {
        return recruiterId;
    }

    public void setRecruiterId(String recruiterId) {
        this.recruiterId = recruiterId;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEligibilityCriteria() {
        return eligibilityCriteria;
    }

    public void setEligibilityCriteria(String eligibilityCriteria) {
        this.eligibilityCriteria = eligibilityCriteria;
    }

    public double getCtc() {
        return ctc;
    }

    public void setCtc(double ctc) {
        this.ctc = ctc;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public JobStatus getStatus() {
        return status;
    }

    public void setStatus(JobStatus status) {
        this.status = status;
    }

    // ✅ Added method to get deadline as LocalDate
    public LocalDate getDeadlineAsLocalDate() {
        return deadline.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
    }
}
