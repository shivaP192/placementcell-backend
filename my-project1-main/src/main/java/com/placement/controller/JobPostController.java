package com.placement.controller;

import com.placement.dto.JobPostDto;
import com.placement.model.JobPost;
import com.placement.service.JobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/jobs")
public class JobPostController {
    @Autowired
    private JobService jobService;
    
    @PostMapping
    public ResponseEntity<JobPost> createJob(@RequestBody JobPostDto jobPostDTO) {
        JobPost jobPost = new JobPost();
        jobPost.setTitle(jobPostDTO.getTitle());
        jobPost.setDescription(jobPostDTO.getDescription());
        jobPost.setEligibilityCriteria(jobPostDTO.getEligibilityCriteria());
        jobPost.setCtc(jobPostDTO.getCtc());
        jobPost.setLocation(jobPostDTO.getLocation());
        jobPost.setDeadline(jobPostDTO.getDeadline());
        
        return ResponseEntity.ok(jobService.createJobPost(jobPost));
    }
    
    @GetMapping
    public ResponseEntity<List<JobPost>> getAllJobs() {
        return ResponseEntity.ok(jobService.getAllJobs());
    }
    
    @GetMapping("/active")
    public ResponseEntity<List<JobPost>> getActiveJobs() {
        return ResponseEntity.ok(jobService.getActiveJobs());
    }
    
    @GetMapping("/recruiter/{recruiterId}")
    public ResponseEntity<List<JobPost>> getJobsByRecruiter(@PathVariable String recruiterId) {
        return ResponseEntity.ok(jobService.getJobsByRecruiter(recruiterId));
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<JobPost> getJobById(@PathVariable String id) {
        return ResponseEntity.of(jobService.getJobById(id));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<JobPost> updateJob(@PathVariable String id, @RequestBody JobPostDto jobPostDTO) {
        JobPost jobPost = new JobPost();
        jobPost.setTitle(jobPostDTO.getTitle());
        jobPost.setDescription(jobPostDTO.getDescription());
        jobPost.setEligibilityCriteria(jobPostDTO.getEligibilityCriteria());
        jobPost.setCtc(jobPostDTO.getCtc());
        jobPost.setLocation(jobPostDTO.getLocation());
        jobPost.setDeadline(jobPostDTO.getDeadline());
        
        return ResponseEntity.ok(jobService.updateJobPost(id, jobPost));
    }
    
    @PutMapping("/{id}/close")
    public ResponseEntity<String> closeJob(@PathVariable String id) {
        jobService.closeJobPost(id);
        return ResponseEntity.ok("Job closed successfully");
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteJob(@PathVariable String id) {
        jobService.deleteJobPost(id);
        return ResponseEntity.ok("Job deleted successfully");
    }
}