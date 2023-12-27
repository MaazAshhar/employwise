package com.assignment.employwise.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;

import java.util.UUID;

public class EmployeeUpdateDto {
    @Size(min=3)
    private String employeeName;

    @Size(min=10,max = 10)
    private String phoneNumber;

    @Email
    private String email;
    private UUID reportTo;
    private String profileImage;

    public String getEmployeeName() {
        return employeeName;
    }

    public void setEmployeeName(String employeeName) {
        this.employeeName = employeeName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public UUID getReportTo() {
        return reportTo;
    }

    public void setReportTo(UUID reportTo) {
        this.reportTo = reportTo;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
