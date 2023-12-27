package com.assignment.employwise.exceptions;

import java.text.MessageFormat;

public class ManagerNotFoundException extends RuntimeException{
    public ManagerNotFoundException(int level,String id,String suffix){
        super(MessageFormat.format("{0}{1} level manager not found for employee_id: {2}",level, suffix ,id));
    }
}
