package com.placement.controller;

import com.placement.dto.JobApplicationDto;
import com.placement.model.JobApplication;
import com.placement.service.ApplicationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/applications")
public class ApplicationController {
    @Autowired
    private ApplicationService applicationService;
    
    @PostMapping
    public ResponseEntity<JobApplication> applyForJob(@RequestBody JobApplicationDto applicationDTO) {
        return ResponseEntity.ok(applicationService.applyForJob(
            applicationDTO.getStudentId(), 
            applicationDTO.getJobId()
        ));
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(applicationService.getApplicationsByStudent(studentId));
    }
    
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<JobApplication>> getApplicationsByJob(@PathVariable String jobId) {
        return ResponseEntity.ok(applicationService.getApplicationsByJob(jobId));
    }
    
    @PutMapping("/{id}/status")
    public ResponseEntity<JobApplication> updateApplicationStatus(
            @PathVariable String id,
            @RequestParam JobApplication.ApplicationStatus status) {
        return ResponseEntity.ok(applicationService.updateApplicationStatus(id, status));
    }
}