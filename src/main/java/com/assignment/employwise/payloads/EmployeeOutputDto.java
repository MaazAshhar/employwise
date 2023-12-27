package com.assignment.employwise.payloads;

import java.util.UUID;

public class EmployeeOutputDto {
    private UUID id;
    private String employeeName;
    private String phoneNumber;
    private String email;
    private UUID reportTo;
    private String profileImage;

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

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
