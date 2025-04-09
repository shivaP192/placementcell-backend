package com.placement.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.Date;

@Document(collection = "password_reset_tokens")
public class PasswordResetToken {
    
    @Id
    private String id;
    
    @Indexed(unique = true)
    private String token;
    
    private String userId;
    
    private Date expiryDate;
    
    // Constructors
    public PasswordResetToken() {}
    
    public PasswordResetToken(String userId, String token, Date expiryDate) {
        this.userId = userId;
        this.token = token;
        this.expiryDate = expiryDate;
    }
    
    // Getters and Setters
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getToken() {
        return token;
    }
    
    public void setToken(String token) {
        this.token = token;
    }
    
    public String getUserId() {
        return userId;
    }
    
    public void setUserId(String userId) {
        this.userId = userId;
    }
    
    public Date getExpiryDate() {
        return expiryDate;
    }
    
    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }
    
    // Utility method to check if token is expired
    public boolean isExpired() {
        return expiryDate.before(new Date());
    }
    
    @Override
    public String toString() {
        return "PasswordResetToken{" +
                "id='" + id + '\'' +
                ", token='" + token + '\'' +
                ", userId='" + userId + '\'' +
                ", expiryDate=" + expiryDate +
                '}';
    }
}