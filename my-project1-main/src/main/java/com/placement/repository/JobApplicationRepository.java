package com.placement.repository;

import com.placement.model.JobApplication;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface JobApplicationRepository extends MongoRepository<JobApplication, String> {
    List<JobApplication> findByStudentId(String studentId);
    List<JobApplication> findByJobId(String jobId);
    List<JobApplication> findByJobIdAndStatus(String jobId, JobApplication.ApplicationStatus status);
    
    boolean existsByStudentIdAndJobId(String studentId, String jobId);  // ✅ Fixed method name
}
