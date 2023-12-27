package com.assignment.employwise.exceptions;

public class InvalidFileFormatException extends RuntimeException{
    String message;

    public InvalidFileFormatException(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
