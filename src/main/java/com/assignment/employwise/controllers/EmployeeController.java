package com.assignment.employwise.controllers;

import com.assignment.employwise.config.AppConstants;
import com.assignment.employwise.payloads.*;
import com.assignment.employwise.services.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.text.MessageFormat;

@RestController
@RequestMapping("api/employees")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    // add employee
    @PostMapping("/add")
    public ResponseEntity<EmployeeResponseDto> addEmployee(@Valid @RequestBody EmployeeInputDto employeeInputDto){
        EmployeeResponseDto employeeResponseDto=this.employeeService.addEmployee(employeeInputDto);
        return new ResponseEntity<>(employeeResponseDto, HttpStatus.CREATED);
    }

    // get all employees

    @GetMapping("/getAll")
    public ResponseEntity<AllEmployeeResponseDto> getAllEmployees(
            @RequestParam(value = "pageNumber",defaultValue = AppConstants.PAGE_NUMBER,required = false) Integer pageNumber,
            @RequestParam(value = "pageSize",defaultValue = AppConstants.PAGE_SIZE,required = false) Integer pageSize,
            @RequestParam(value = "sortBy",defaultValue = AppConstants.SORT_BY,required = false) String sortBy,
            @RequestParam(value="sortDir",defaultValue = AppConstants.SORT_DIR,required = false) Boolean sortDir
    ){
        AllEmployeeResponseDto allEmployeeResponseDto=this.employeeService.getAllEmployees(pageNumber,pageSize,sortBy,sortDir);
        return new ResponseEntity<>(allEmployeeResponseDto,HttpStatus.OK);
    }

    // delete employee
    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse> deleteEmployeeById(@PathVariable("id") String id){
        this.employeeService.deleteEmployeeById(id);
        ApiResponse apiResponse=new ApiResponse(MessageFormat.format("Employee associated to id {0} has been deleted successfully.",id));
        return new ResponseEntity<>(apiResponse,HttpStatus.ACCEPTED);
    }

    // update employee by id
    @PutMapping("/{id}")
    public ResponseEntity<ApiResponse> updateEmpolyee(
            @Valid @PathVariable("id") String id,
            @RequestBody EmployeeUpdateDto employeeUpdateDto
            ){
        EmployeeResponseDto employeeResponseDto=this.employeeService.updateEmployeeById(employeeUpdateDto,id);
        ApiResponse apiResponse=new ApiResponse(MessageFormat.format("Employee with id {0} has been successfully updated",id));
        return new ResponseEntity<>(apiResponse,HttpStatus.ACCEPTED);
    }

    // get nth level manager
    @GetMapping("/manager/{id}")
    public  ResponseEntity<EmployeeOutputDto> getNthLevelManager(
            @PathVariable("id") String id,
            @RequestParam(value = "level",required = false,defaultValue = AppConstants.DEFAULT_LEVEL_MANAGER) int level
    ){
        EmployeeOutputDto employeeOutputDto=this.employeeService.getNthLevelManagerOfEmployee(id,level);
        return new ResponseEntity<>(employeeOutputDto,HttpStatus.FOUND);
    }
}
