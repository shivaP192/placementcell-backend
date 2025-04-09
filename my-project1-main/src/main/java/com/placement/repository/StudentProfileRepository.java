package com.placement.repository;

import com.placement.model.StudentProfile;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface StudentProfileRepository extends MongoRepository<StudentProfile, String> {
    Optional<StudentProfile> findByUserId(String userId);
    List<StudentProfile> findByBranch(String branch);
    List<StudentProfile> findByStatus(StudentProfile.PlacementStatus status);
    Optional<StudentProfile> findByRollNumber(String rollNumber);
    boolean existsByUserId(String userId);
}