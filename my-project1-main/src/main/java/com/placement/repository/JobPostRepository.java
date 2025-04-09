package com.placement.repository;

import com.placement.model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDate;
import java.util.List;

public interface JobPostRepository extends MongoRepository<JobPost, String> {
    List<JobPost> findByRecruiterId(String recruiterId);
    List<JobPost> findByCompanyId(String companyId);
    List<JobPost> findByStatus(JobPost.JobStatus status);
    List<JobPost> findByDeadlineAfter(LocalDate currentDate);
}
