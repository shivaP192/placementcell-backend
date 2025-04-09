package com.placement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "job_applications")
public class JobApplication {
    @Id
    private String id;
    private String studentId;
    private String jobId;
    private Date appliedAt;
    private ApplicationStatus status;

    // Enums
    public enum ApplicationStatus {
        APPLIED, SHORTLISTED, REJECTED, SELECTED
    }

    // Constructors
    public JobApplication() {
        this.appliedAt = new Date();
        this.status = ApplicationStatus.APPLIED;
    }

    public JobApplication(String studentId, String jobId) {
        this();
        this.studentId = studentId;
        this.jobId = jobId;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public Date getAppliedAt() {
        return appliedAt;
    }

    public void setAppliedAt(Date appliedAt) {
        this.appliedAt = appliedAt;
    }

    public ApplicationStatus getStatus() {
        return status;
    }

    public void setStatus(ApplicationStatus status) {
        this.status = status;
    }
}