package com.placement.dto;



import lombok.Builder;
import lombok.Data;

@Data
@Builder

public class CompanyProfileDto {
    private String id;
    private String userId;
    private String companyName;
    private String address;
    private String website;
    private String status;
    private RegisterRequest user;
}