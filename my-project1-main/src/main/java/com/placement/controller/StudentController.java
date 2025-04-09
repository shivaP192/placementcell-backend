package com.placement.controller;

import com.placement.dto.StudentProfileDto;
import com.placement.model.StudentProfile;
import com.placement.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    @Autowired
    private StudentService studentService;
    
    @PostMapping("/{userId}/profile")
    public ResponseEntity<StudentProfile> createProfile(
            @PathVariable String userId,
            @RequestBody StudentProfileDto profileDTO) {
        StudentProfile profile = new StudentProfile();
        profile.setUserId(userId);
        profile.setRollNumber(profileDTO.getRollNumber());
        profile.setBranch(profileDTO.getBranch());
        profile.setCgpa(profileDTO.getCgpa());
        profile.setSkills(profileDTO.getSkills());
        profile.setBacklogs(profileDTO.getBacklogs());
        
        return ResponseEntity.ok(studentService.createStudentProfile(profile));
    }
    
    @GetMapping("/{userId}/profile")
    public ResponseEntity<StudentProfile> getProfile(@PathVariable String userId) {
        return ResponseEntity.of(studentService.getStudentProfile(userId));
    }
    
    @PutMapping("/{userId}/profile")
    public ResponseEntity<StudentProfile> updateProfile(
            @PathVariable String userId,
            @RequestBody StudentProfileDto profileDTO) {
        StudentProfile profile = new StudentProfile();
        profile.setRollNumber(profileDTO.getRollNumber());
        profile.setBranch(profileDTO.getBranch());
        profile.setCgpa(profileDTO.getCgpa());
        profile.setSkills(profileDTO.getSkills());
        profile.setBacklogs(profileDTO.getBacklogs());
        
        return ResponseEntity.ok(studentService.updateStudentProfile(userId, profile));
    }
    
    @PostMapping("/{userId}/upload-resume")
    public ResponseEntity<String> uploadResume(
            @PathVariable String userId,
            @RequestParam("file") MultipartFile file) throws IOException {
        return ResponseEntity.ok(studentService.uploadResume(userId, file));
    }
}