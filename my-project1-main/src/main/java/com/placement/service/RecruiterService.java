package com.placement.service;

import com.placement.model.CompanyProfile;
import com.placement.repository.CompanyProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class RecruiterService {
    @Autowired
    private CompanyProfileRepository companyProfileRepository;
    
    public CompanyProfile createCompanyProfile(CompanyProfile profile) {
        if (companyProfileRepository.existsByUserId(profile.getUserId())) {
            throw new RuntimeException("Company profile already exists for this user");
        }
        
        return companyProfileRepository.save(profile);
    }
    
    public Optional<CompanyProfile> getCompanyProfile(String userId) {
        return companyProfileRepository.findById(userId);
    }
    
    public CompanyProfile updateCompanyProfile(String userId, CompanyProfile updatedProfile) {
        Optional<CompanyProfile> profileOptional = companyProfileRepository.findById(userId);
        if (profileOptional.isEmpty()) {
            throw new RuntimeException("Profile not found");
        }
        
        CompanyProfile profile = profileOptional.get();
        profile.setCompanyName(updatedProfile.getCompanyName());
        profile.setAddress(updatedProfile.getAddress());
        profile.setWebsite(updatedProfile.getWebsite());
        
        return companyProfileRepository.save(profile);
    }
}