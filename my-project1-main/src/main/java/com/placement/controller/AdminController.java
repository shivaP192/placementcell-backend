package com.placement.controller;

import com.placement.model.CompanyProfile;
import com.placement.model.User;
import com.placement.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    private AdminService adminService;
    
    @GetMapping("/students")
    public ResponseEntity<List<User>> getAllStudents() {
        return ResponseEntity.ok(adminService.getAllStudents());
    }
    
    @GetMapping("/recruiters")
    public ResponseEntity<List<User>> getAllRecruiters() {
        return ResponseEntity.ok(adminService.getAllRecruiters());
    }
    
    @GetMapping("/companies/pending")
    public ResponseEntity<List<CompanyProfile>> getPendingCompanies() {
        return ResponseEntity.ok(adminService.getPendingCompanies());
    }
    
    @PutMapping("/companies/{id}/approve")
    public ResponseEntity<CompanyProfile> approveCompany(@PathVariable String id) {
        return ResponseEntity.ok(adminService.approveCompany(id));
    }
    
    @PutMapping("/companies/{id}/reject")
    public ResponseEntity<CompanyProfile> rejectCompany(@PathVariable String id) {
        return ResponseEntity.ok(adminService.rejectCompany(id));
    }
}