package com.assignment.employwise.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.UUID;

@Document(collection = "employees")
public class Employee {

    @Id
    private UUID id;
    private String employeeName;
    private String phoneNumber;
    @Indexed(unique = true)
    private String email;
    private UUID reportTo;
    private String profileImage;

    public Employee() {
    }

    public Employee(String employeeName, String phoneNumber, String email, UUID reportTo, String profileImage) {
        this.employeeName = employeeName;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.reportTo = reportTo;
        this.profileImage = profileImage;
    }

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
