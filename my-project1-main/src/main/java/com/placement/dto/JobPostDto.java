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
public class JobPostDto {
    private String id;
    private String recruiterId;
    private String companyId;
    private String companyName;
    private String title;
    private String description;
    private String eligibilityCriteria;
    private double ctc;
    private String location;
    private Date deadline;
    private String status;
}