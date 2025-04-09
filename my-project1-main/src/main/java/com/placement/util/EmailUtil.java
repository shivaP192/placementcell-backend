package com.placement.util;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.placement.service.EmailService;

@Component
public class EmailUtil {
    @Autowired
    private EmailService emailService;
    
    public void sendPasswordResetEmail(String email, String token) {
        emailService.sendPasswordResetEmail(email, token);
    }
    
    public void sendApplicationStatusUpdate(String studentEmail, String jobTitle, String status) {
        emailService.sendApplicationStatusUpdate(studentEmail, jobTitle, status);
    }
    
    public void sendInterviewScheduled(String studentEmail, String companyName, Date interviewDate) {
        emailService.sendInterviewScheduled(studentEmail, companyName, interviewDate);
    }
}