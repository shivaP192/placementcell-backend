package com.placement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document(collection = "interview_schedules")
public class InterviewSchedule {
    @Id
    private String id;
    private String jobId;
    private String studentId;
    private Date scheduleDate;
    private InterviewMode mode;
    private String linkOrVenue;

    // Enums
    public enum InterviewMode {
        ONLINE, OFFLINE
    }

    // Constructors
    public InterviewSchedule() {}

    public InterviewSchedule(String jobId, String studentId, Date scheduleDate, InterviewMode mode) {
        this.jobId = jobId;
        this.studentId = studentId;
        this.scheduleDate = scheduleDate;
        this.mode = mode;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getJobId() {
        return jobId;
    }

    public void setJobId(String jobId) {
        this.jobId = jobId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Date getScheduleDate() {
        return scheduleDate;
    }

    public void setScheduleDate(Date scheduleDate) {
        this.scheduleDate = scheduleDate;
    }

    public InterviewMode getMode() {
        return mode;
    }

    public void setMode(InterviewMode mode) {
        this.mode = mode;
    }

    public String getLinkOrVenue() {
        return linkOrVenue;
    }

    public void setLinkOrVenue(String linkOrVenue) {
        this.linkOrVenue = linkOrVenue;
    }

    public String getApplicationId() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getApplicationId'");
    }

    public void setMode(String mode2) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'setMode'");
    }
}