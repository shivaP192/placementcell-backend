package com.placement.controller;

import com.placement.dto.InterviewScheduleDTO;
import com.placement.model.InterviewSchedule;
import com.placement.service.InterviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/interviews")
public class InterviewController {
    @Autowired
    private InterviewService interviewService;
    
    @PostMapping
    public ResponseEntity<InterviewSchedule> scheduleInterview(@RequestBody InterviewScheduleDTO interviewDTO) {
        InterviewSchedule interview = new InterviewSchedule();
        interview.setJobId(interviewDTO.getJobId());
        interview.setStudentId(interviewDTO.getStudentId());
        interview.setScheduleDate(interviewDTO.getScheduleDate());
        interview.setMode(interviewDTO.getMode());
        interview.setLinkOrVenue(interviewDTO.getLinkOrVenue());
        
        return ResponseEntity.ok(interviewService.scheduleInterview(interview));
    }
    
    @GetMapping("/student/{studentId}")
    public ResponseEntity<List<InterviewSchedule>> getInterviewsByStudent(@PathVariable String studentId) {
        return ResponseEntity.ok(interviewService.getInterviewsByStudent(studentId));
    }
    
    @GetMapping("/job/{jobId}")
    public ResponseEntity<List<InterviewSchedule>> getInterviewsByJob(@PathVariable String jobId) {
        return ResponseEntity.ok(interviewService.getInterviewsByJob(jobId));
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<InterviewSchedule> updateInterview(
            @PathVariable String id,
            @RequestBody InterviewScheduleDTO interviewDTO) {
        InterviewSchedule interview = new InterviewSchedule();
        interview.setScheduleDate(interviewDTO.getScheduleDate());
        interview.setMode(interviewDTO.getMode());
        interview.setLinkOrVenue(interviewDTO.getLinkOrVenue());
        
        return ResponseEntity.ok(interviewService.updateInterview(id, interview));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<String> cancelInterview(@PathVariable String id) {
        interviewService.cancelInterview(id);
        return ResponseEntity.ok("Interview cancelled successfully");
    }
}