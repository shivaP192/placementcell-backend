package com.placement.service;

import com.placement.model.InterviewSchedule;
import com.placement.model.JobApplication;
import com.placement.repository.InterviewScheduleRepository;
import com.placement.repository.JobApplicationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InterviewService {
    @Autowired
    private InterviewScheduleRepository interviewRepository;
    
    @Autowired
    private JobApplicationRepository applicationRepository;
    
    public InterviewSchedule scheduleInterview(InterviewSchedule interview) {
        // Check if application exists and is shortlisted
        boolean applicationExists = applicationRepository.existsByStudentIdAndJobId(
            interview.getStudentId(), interview.getJobId()
        );

        if (!applicationExists) {
            throw new RuntimeException("Application not found");
        }
        
        Optional<JobApplication> applicationOptional = applicationRepository.findById(interview.getApplicationId());
        if (applicationOptional.isEmpty()) {
            throw new RuntimeException("Job application not found");
        }

        JobApplication application = applicationOptional.get();
        if (application.getStatus() != JobApplication.ApplicationStatus.SHORTLISTED) {
            throw new RuntimeException("Application is not shortlisted");
        }
        
        // Check if interview already scheduled
        if (interviewRepository.existsByStudentIdAndJobId(interview.getStudentId(), interview.getJobId())) {
            throw new RuntimeException("Interview already scheduled for this application");
        }
        
        return interviewRepository.save(interview);
    }
    
    public List<InterviewSchedule> getInterviewsByStudent(String studentId) {
        return interviewRepository.findByStudentId(studentId);
    }
    
    public List<InterviewSchedule> getInterviewsByJob(String jobId) {
        return interviewRepository.findByJobId(jobId);
    }
    
    public InterviewSchedule updateInterview(String id, InterviewSchedule updatedInterview) {
        Optional<InterviewSchedule> interviewOptional = interviewRepository.findById(id);
        if (interviewOptional.isEmpty()) {
            throw new RuntimeException("Interview not found");
        }
        
        InterviewSchedule interview = interviewOptional.get();
        interview.setScheduleDate(updatedInterview.getScheduleDate());
        interview.setMode(updatedInterview.getMode());
        interview.setLinkOrVenue(updatedInterview.getLinkOrVenue());
        
        return interviewRepository.save(interview);
    }
    
    public void cancelInterview(String id) {
        interviewRepository.deleteById(id);
    }
}
