package com.assignment.employwise.exceptions;

import java.text.MessageFormat;

public class ResourceNotFoundException extends RuntimeException{
    String resourceName;
    String fieldName;
    String fieldValue;

    public ResourceNotFoundException(String resourceName, String fieldName, String fieldValue) {
        super(MessageFormat.format("{0} not found with {1}: {2}",resourceName,fieldName,fieldValue));
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public ResourceNotFoundException(){
        super("Reporting manager doesn't exist");
    }
}
