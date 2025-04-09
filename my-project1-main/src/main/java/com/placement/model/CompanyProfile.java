package com.placement.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "company_profiles")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CompanyProfile {
    @Id
    private String id;
    private String userId;
    private String companyName;
    private String address;
    private String website;
    private ApprovalStatus status;
    private String userRole; // Added field for user role

    // Enum for Approval Status
    public enum ApprovalStatus {
        PENDING, APPROVED, REJECTED
    }
}
