package com.placement.repository;

import com.placement.model.InterviewSchedule;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface InterviewScheduleRepository extends MongoRepository<InterviewSchedule, String> {
    List<InterviewSchedule> findByStudentId(String studentId);
    List<InterviewSchedule> findByJobId(String jobId);
    List<InterviewSchedule> findByScheduleDateAfter(LocalDateTime currentDate);
    boolean existsByStudentIdAndJobId(String studentId, String jobId);
}
