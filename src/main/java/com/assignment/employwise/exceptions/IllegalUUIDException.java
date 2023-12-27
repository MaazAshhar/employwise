package com.assignment.employwise.exceptions;

public class IllegalUUIDException extends RuntimeException{
    String message="not a valid UUID";

    public IllegalUUIDException() {
    }

    public IllegalUUIDException(String message) {
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
