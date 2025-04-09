package com.placement.service;

import com.placement.model.JobApplication;
import com.placement.model.JobPost;
import com.placement.model.StudentProfile;
import com.placement.repository.JobApplicationRepository;
import com.placement.repository.JobPostRepository;
import com.placement.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;
import java.util.Optional;

@Service
public class ApplicationService {
    @Autowired
    private JobApplicationRepository applicationRepository;

    @Autowired
    private JobPostRepository jobPostRepository;

    @Autowired
    private StudentProfileRepository studentProfileRepository;

    public JobApplication applyForJob(String studentId, String jobId) {
        // Check if already applied
        if (applicationRepository.existsByStudentIdAndJobId(studentId, jobId)) {
            throw new RuntimeException("Already applied for this job");
        }

        // Check job exists and is active
        Optional<JobPost> jobOptional = jobPostRepository.findById(jobId);
        if (jobOptional.isEmpty()) {
            throw new RuntimeException("Job not found");
        }

        JobPost job = jobOptional.get();
        if (job.getStatus() != JobPost.JobStatus.ACTIVE) {
            throw new RuntimeException("Job is not active");
        }

        // ✅ Corrected Date comparison using LocalDate
        LocalDate jobDeadline = job.getDeadline().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        if (jobDeadline.isBefore(LocalDate.now())) {
            throw new RuntimeException("Application deadline has passed");
        }

        // Check student eligibility
        Optional<StudentProfile> profileOptional = studentProfileRepository.findByUserId(studentId);
        if (profileOptional.isEmpty()) {
            throw new RuntimeException("Student profile not found");
        }

        @SuppressWarnings("unused")
        StudentProfile profile = profileOptional.get();
        // Add additional eligibility checks here based on job criteria

        // Create application
        JobApplication application = new JobApplication(studentId, jobId);
        return applicationRepository.save(application);
    }

    public List<JobApplication> getApplicationsByStudent(String studentId) {
        return applicationRepository.findByStudentId(studentId);
    }

    public List<JobApplication> getApplicationsByJob(String jobId) {
        return applicationRepository.findByJobId(jobId);
    }

    public JobApplication updateApplicationStatus(String applicationId, JobApplication.ApplicationStatus status) {
        Optional<JobApplication> applicationOptional = applicationRepository.findById(applicationId);
        if (applicationOptional.isEmpty()) {
            throw new RuntimeException("Application not found");
        }

        JobApplication application = applicationOptional.get();
        application.setStatus(status);
        return applicationRepository.save(application);
    }
}
