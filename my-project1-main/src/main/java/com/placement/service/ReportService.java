package com.placement.service;

import com.placement.model.StudentProfile;
import com.placement.repository.StudentProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private StudentProfileRepository studentProfileRepository;
    
    public Map<String, Long> getPlacementStatsByBranch() {
        List<StudentProfile> profiles = studentProfileRepository.findAll();
        
        return profiles.stream()
            .collect(Collectors.groupingBy(
                StudentProfile::getBranch,
                Collectors.counting()
            ));
    }
    
    public Map<StudentProfile.PlacementStatus, Long> getOverallPlacementStats() {
        List<StudentProfile> profiles = studentProfileRepository.findAll();
        
        return profiles.stream()
            .collect(Collectors.groupingBy(
                StudentProfile::getStatus,
                Collectors.counting()
            ));
    }
    
    public List<StudentProfile> getPlacedStudents() {
        return studentProfileRepository.findByStatus(StudentProfile.PlacementStatus.PLACED);
    }
    
    public List<StudentProfile> getUnplacedStudents() {
        return studentProfileRepository.findByStatus(StudentProfile.PlacementStatus.UNPLACED);
    }
}