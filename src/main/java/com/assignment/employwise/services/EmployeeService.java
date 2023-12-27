package com.assignment.employwise.services;

import com.assignment.employwise.payloads.*;

public interface EmployeeService {
    public EmployeeResponseDto addEmployee(EmployeeInputDto employeeInputDto);
    public AllEmployeeResponseDto getAllEmployees(Integer pageNumber,Integer pageSize,String sortBy,Boolean sortDir);
    public void deleteEmployeeById(String id);
    public EmployeeResponseDto updateEmployeeById(EmployeeUpdateDto employeeDto, String id);
    public EmployeeOutputDto getNthLevelManagerOfEmployee(String id, int n);
}
