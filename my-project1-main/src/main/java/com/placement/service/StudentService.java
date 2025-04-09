package com.placement.service;

import com.placement.model.StudentProfile;
import com.placement.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Service
public class StudentService {
    @Autowired
    private StudentProfileRepository studentProfileRepository;
    
    private final String UPLOAD_DIR = "uploads/resumes/";
    
    public StudentProfile createStudentProfile(StudentProfile profile) {
        if (studentProfileRepository.existsByUserId(profile.getUserId())) {
            throw new RuntimeException("Profile already exists for this user");
        }
        
        return studentProfileRepository.save(profile);
    }
    
    public Optional<StudentProfile> getStudentProfile(String userId) {
        return studentProfileRepository.findByUserId(userId);
    }
    
    public StudentProfile updateStudentProfile(String userId, StudentProfile updatedProfile) {
        Optional<StudentProfile> profileOptional = studentProfileRepository.findByUserId(userId);
        if (profileOptional.isEmpty()) {
            throw new RuntimeException("Profile not found");
        }
        
        StudentProfile profile = profileOptional.get();
        profile.setRollNumber(updatedProfile.getRollNumber());
        profile.setBranch(updatedProfile.getBranch());
        profile.setCgpa(updatedProfile.getCgpa());
        profile.setSkills(updatedProfile.getSkills());
        profile.setBacklogs(updatedProfile.getBacklogs());
        
        return studentProfileRepository.save(profile);
    }
    
    public String uploadResume(String userId, MultipartFile file) throws IOException {
        Optional<StudentProfile> profileOptional = studentProfileRepository.findByUserId(userId);
        if (profileOptional.isEmpty()) {
            throw new RuntimeException("Profile not found");
        }
        
        // Create upload directory if it doesn't exist
        Path uploadPath = Paths.get(UPLOAD_DIR);
        if (!Files.exists(uploadPath)) {
            Files.createDirectories(uploadPath);
        }
        
        // Save file
        String fileName = userId + "_" + System.currentTimeMillis() + ".pdf";
        Path filePath = uploadPath.resolve(fileName);
        Files.copy(file.getInputStream(), filePath);
        
        // Update profile with resume URL
        StudentProfile profile = profileOptional.get();
        profile.setResumeUrl(filePath.toString());
        studentProfileRepository.save(profile);
        
        return filePath.toString();
    }
}