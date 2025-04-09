package com.placement.service;

import com.placement.model.CompanyProfile;
import com.placement.model.User;
import com.placement.repository.CompanyProfileRepository;
import com.placement.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class AdminService {
    
    private final UserRepository userRepository;
    private final CompanyProfileRepository companyProfileRepository;
    
    @Autowired
    public AdminService(UserRepository userRepository, CompanyProfileRepository companyProfileRepository) {
        this.userRepository = userRepository;
        this.companyProfileRepository = companyProfileRepository;
    }
    
    public List<User> getAllStudents() {
        return userRepository.findByRole(User.UserRole.STUDENT);
    }
    
    public List<User> getAllRecruiters() {
        return userRepository.findByRole(User.UserRole.RECRUITER);
    }
    
    public List<CompanyProfile> getPendingCompanies() {
        return companyProfileRepository.findByStatus(CompanyProfile.ApprovalStatus.PENDING);
    }
    
    public CompanyProfile updateCompanyStatus(String companyId, CompanyProfile.ApprovalStatus status) {
        return companyProfileRepository.findById(companyId)
            .map(company -> {
                company.setStatus(status);
                return companyProfileRepository.save(company);
            })
            .orElseThrow(() -> new RuntimeException("Company not found"));
    }
    
    public CompanyProfile approveCompany(String companyId) {
        return updateCompanyStatus(companyId, CompanyProfile.ApprovalStatus.APPROVED);
    }
    
    public CompanyProfile rejectCompany(String companyId) {
        return updateCompanyStatus(companyId, CompanyProfile.ApprovalStatus.REJECTED);
    }
}
