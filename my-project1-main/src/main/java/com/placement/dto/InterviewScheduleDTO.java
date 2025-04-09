package com.placement.dto;

import java.util.Date;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor



public class InterviewScheduleDTO {
    private String id;
    private String jobId;
    private String studentId;
    private Date scheduleDate;
    private String mode;
    private String linkOrVenue;
    private JobPostDto jobDetails;
    private StudentProfileDto studentDetails;
}