package com.placement.dto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StudentProfileDto {
    private String id;
    private String userId;
    private String rollNumber;
    private String branch;
    private String resumeUrl;
    private double cgpa;
    private String[] skills;
    private int backlogs;
    private String status;
    private RegisterRequest userDetails;
    
}
