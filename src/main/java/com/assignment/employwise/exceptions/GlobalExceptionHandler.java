package com.assignment.employwise.exceptions;

import com.assignment.employwise.payloads.ApiResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(InvalidFileFormatException.class)
    public ResponseEntity<ApiResponse> invalidFileFormatExceptionHandler(InvalidFileFormatException e){
        ApiResponse apiResponse=new ApiResponse(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(IllegalUUIDException.class)
    public ResponseEntity<ApiResponse> illegalUUIDExceptionHandler(IllegalUUIDException e){
        String message=e.getMessage();
        ApiResponse apiResponse=new ApiResponse(message);
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException e){
        ApiResponse apiResponse=new ApiResponse(e.getMessage());
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(DuplicateEmailException.class)
    public ResponseEntity<ApiResponse> duplicateEmailExceptionHandler(DuplicateEmailException e){
        ApiResponse apiResponse=new ApiResponse(e.getMessage());
        return new ResponseEntity<>(apiResponse,HttpStatus.NOT_ACCEPTABLE);
    }
    @ExceptionHandler(InvalidPageOrLevelException.class)
    public ResponseEntity<ApiResponse> invalidPageExceptionHandler(InvalidPageOrLevelException e){
        ApiResponse apiResponse=new ApiResponse(e.getMessage());
        return new ResponseEntity<>(apiResponse,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ManagerNotFoundException.class)
    public ResponseEntity<ApiResponse> managerNotFoundExceptionHandler(ManagerNotFoundException e){
        ApiResponse apiResponse = new ApiResponse(e.getMessage());
        return new ResponseEntity<>(apiResponse, HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex){
        Map<String,String> resp=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach((error)->{
            String fieldName=error.getField();
            String errorMessage=error.getDefaultMessage();
            resp.put(fieldName,errorMessage);
        });
        return new ResponseEntity<>(resp,HttpStatus.BAD_REQUEST);
    }
}
