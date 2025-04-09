package com.placement.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Date;

@SuppressWarnings("unused")
@Service
public class EmailService {
    @Autowired
    private JavaMailSender mailSender;
    
    public void sendSimpleEmail(String to, String subject, String text) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(to);
        message.setSubject(subject);
        message.setText(text);
        mailSender.send(message);
    }
    
    public void sendApplicationStatusUpdate(String studentEmail, String jobTitle, String status) {
        String subject = "Application Status Update";
        String text = String.format(
            "Your application for %s has been updated to %s status.",
            jobTitle, status
        );
        sendSimpleEmail(studentEmail, subject, text);
    }
    
    public void sendInterviewScheduled(String studentEmail, String companyName, Date interviewDate) {
        String subject = "Interview Scheduled";
        String text = String.format(
            "An interview with %s has been scheduled for %s.",
            companyName, interviewDate.toString()
        );
        sendSimpleEmail(studentEmail, subject, text);
    }
    
    public void sendPasswordResetEmail(String email, String token) {
        String subject = "Password Reset Request";
        String resetLink = "http://yourdomain.com/reset-password?token=" + token;
        String text = String.format(
            "To reset your password, click the following link: %s\n\n" +
            "This link will expire in 1 hour.",
            resetLink
        );
        sendSimpleEmail(email, subject, text);
    }
}
