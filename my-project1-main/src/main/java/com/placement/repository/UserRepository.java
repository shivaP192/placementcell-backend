package com.placement.repository;

import com.placement.model.User;
import com.placement.model.User.UserRole;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
    
    Optional<User> findByEmail(String email);
    
    boolean existsByEmail(String email);

    @Query("{ '_id': ?0, 'role': ?1 }")
    Optional<User> findByIdAndRole(String id, String role);  // Use `role.name()` while calling

    @Query("{ 'role': ?0 }")
    List<User> findByRole(UserRole recruiter);

    @SuppressWarnings("unchecked")
    User save(User user);  // Fix `saveAll` issue
}
