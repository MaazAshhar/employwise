package com.assignment.employwise.exceptions;

public class InvalidPageOrLevelException extends RuntimeException{
    private String message;
    public InvalidPageOrLevelException(String message){
        this.message=message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
