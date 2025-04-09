package com.placement.repository;

import com.placement.model.CompanyProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface CompanyProfileRepository extends MongoRepository<CompanyProfile, String> {
    Optional<CompanyProfile> findByUserRole(String userRole);
    List<CompanyProfile> findByStatus(CompanyProfile.ApprovalStatus status);
    Optional<CompanyProfile> findByCompanyName(String companyName);
    boolean existsByUserId(String userId);
}