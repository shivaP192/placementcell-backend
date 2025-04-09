package com.placement.controller;

import com.placement.dto.CompanyProfileDto;
import com.placement.model.CompanyProfile;
import com.placement.service.RecruiterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/recruiters")
public class RecruiterController {
    @Autowired
    private RecruiterService recruiterService;
    
    @PostMapping("/{userId}/company")
    public ResponseEntity<CompanyProfile> createCompanyProfile(
            @PathVariable String userId,
            @RequestBody CompanyProfileDto profileDTO) {
        CompanyProfile profile = new CompanyProfile();
        profile.setUserId(userId);
        profile.setCompanyName(profileDTO.getCompanyName());
        profile.setAddress(profileDTO.getAddress());
        profile.setWebsite(profileDTO.getWebsite());
        
        return ResponseEntity.ok(recruiterService.createCompanyProfile(profile));
    }
    
    @GetMapping("/{userId}/company")
    public ResponseEntity<CompanyProfile> getCompanyProfile(@PathVariable String userId) {
        return ResponseEntity.of(recruiterService.getCompanyProfile(userId));
    }
    
    @PutMapping("/{userId}/company")
    public ResponseEntity<CompanyProfile> updateCompanyProfile(
            @PathVariable String userId,
            @RequestBody CompanyProfileDto profileDTO) {
        CompanyProfile profile = new CompanyProfile();
        profile.setCompanyName(profileDTO.getCompanyName());
        profile.setAddress(profileDTO.getAddress());
        profile.setWebsite(profileDTO.getWebsite());
        
        return ResponseEntity.ok(recruiterService.updateCompanyProfile(userId, profile));
    }
}