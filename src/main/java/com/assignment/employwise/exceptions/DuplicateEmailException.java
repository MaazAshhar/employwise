package com.assignment.employwise.exceptions;

import java.text.MessageFormat;

public class DuplicateEmailException extends RuntimeException{
    private String resourceName;
    private String email;
    public DuplicateEmailException(String resourceName,String email){
        super(MessageFormat.format("{0} with email {1} already exist",resourceName,email));
        this.resourceName=resourceName;
        this.email=email;
    }
}
