package com.placement.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class JobApplicationDto {
    private String id;
    private String studentId;
    private String jobId;
    private Date appliedAt;
    private String status;
    private StudentProfileDto studentDetails;
    private JobPostDto jobDetails;
}