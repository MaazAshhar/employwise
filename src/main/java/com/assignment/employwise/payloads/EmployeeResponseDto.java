package com.assignment.employwise.payloads;

import java.util.UUID;

public class EmployeeResponseDto {
    private UUID id;

    private final String message = "Employee added successfully";

    public EmployeeResponseDto(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }
}
