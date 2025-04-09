package com.placement.service;

import com.placement.model.JobPost;
import com.placement.repository.JobPostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class JobService {
    @Autowired
    private JobPostRepository jobPostRepository;
    
    public JobPost createJobPost(JobPost jobPost) {
        return jobPostRepository.save(jobPost);
    }
    
    public List<JobPost> getAllJobs() {
        return jobPostRepository.findAll();
    }
    
    public List<JobPost> getJobsByRecruiter(String recruiterId) {
        return jobPostRepository.findByRecruiterId(recruiterId);
    }
    
    public List<JobPost> getActiveJobs() {
        return jobPostRepository.findByStatus(JobPost.JobStatus.ACTIVE);
    }
    
    public Optional<JobPost> getJobById(String id) {
        return jobPostRepository.findById(id);
    }
    
    public JobPost updateJobPost(String id, JobPost updatedJob) {
        Optional<JobPost> jobOptional = jobPostRepository.findById(id);
        if (jobOptional.isEmpty()) {
            throw new RuntimeException("Job not found");
        }
        
        JobPost job = jobOptional.get();
        job.setTitle(updatedJob.getTitle());
        job.setDescription(updatedJob.getDescription());
        job.setEligibilityCriteria(updatedJob.getEligibilityCriteria());
        job.setCtc(updatedJob.getCtc());
        job.setLocation(updatedJob.getLocation());
        job.setDeadline(updatedJob.getDeadline());
        
        return jobPostRepository.save(job);
    }
    
    public void closeJobPost(String id) {
        Optional<JobPost> jobOptional = jobPostRepository.findById(id);
        if (jobOptional.isEmpty()) {
            throw new RuntimeException("Job not found");
        }
        
        JobPost job = jobOptional.get();
        job.setStatus(JobPost.JobStatus.CLOSED);
        jobPostRepository.save(job);
    }
    
    public void deleteJobPost(String id) {
        jobPostRepository.deleteById(id);
    }
}