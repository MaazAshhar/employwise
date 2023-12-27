package com.assignment.employwise.services.impl;

import com.assignment.employwise.exceptions.*;
import com.assignment.employwise.models.Employee;
import com.assignment.employwise.payloads.*;
import com.assignment.employwise.repositories.EmployeeRepo;
import com.assignment.employwise.services.EmailService;
import com.assignment.employwise.services.EmployeeService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private EmployeeRepo employeeRepo;

    @Autowired
    private EmailService emailService;

    // add employee
    @Override
    public EmployeeResponseDto addEmployee(EmployeeInputDto employeeInputDto) {
        Employee employee=this.modelMapper.map(employeeInputDto,Employee.class);
        try{
            employee.setId(UUID.randomUUID());
            UUID reportTo = employeeInputDto.getReportTo();
            Employee manager = null;
            if(reportTo != null){
                manager = this.employeeRepo.findById(reportTo).orElseThrow(() -> new ResourceNotFoundException());
            }
            Employee savedEmployee=this.employeeRepo.save(employee);
            if(manager != null){
                this.emailService.sendEmailToManager(savedEmployee.getEmployeeName(), manager.getEmail(), savedEmployee.getEmail(), savedEmployee.getPhoneNumber());
            }
            EmployeeResponseDto employeeResponseDto=new EmployeeResponseDto(savedEmployee.getId());
            return employeeResponseDto;
        }catch (DataIntegrityViolationException e){
            throw new DuplicateEmailException("Employee",employeeInputDto.getEmail());
        }
    }


    // get all employees
    @Override
    public AllEmployeeResponseDto getAllEmployees(Integer pageNumber,Integer pageSize,String sortBy,Boolean sortDir) {
        if(pageNumber<0)throw new InvalidPageOrLevelException("page number must not be less than 0");
        if(pageSize<1)throw new InvalidPageOrLevelException("page size must be greater than 0");
        Sort sort= Sort.by(sortBy).ascending();
        if(!sortDir){
            sort=Sort.by(sortBy).descending();
        }
        Pageable page= PageRequest.of(pageNumber,pageSize,sort);
        Page<Employee> pageEmployees=this.employeeRepo.findAll(page);
        List<Employee> employees=pageEmployees.getContent();
        List<EmployeeOutputDto> employeeOutputDtos=new ArrayList<>();
        for(Employee emp:employees){
            employeeOutputDtos.add(this.modelMapper.map(emp, EmployeeOutputDto.class));
        }
        AllEmployeeResponseDto allEmployeeResponse=new AllEmployeeResponseDto();
        allEmployeeResponse.setEmployees(employeeOutputDtos);
        allEmployeeResponse.setPageNumber(pageEmployees.getNumber());
        allEmployeeResponse.setPageSize(pageEmployees.getSize());
        allEmployeeResponse.setTotalElements(pageEmployees.getTotalElements());
        allEmployeeResponse.setTotalPages(pageEmployees.getTotalPages());
        allEmployeeResponse.setLastPage(pageEmployees.isLast());
        return allEmployeeResponse;
    }

    //delete employee
    @Override
    public void deleteEmployeeById(String id) {
        UUID userIds = null;
        try {
            userIds = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalUUIDException();
        }
        Employee employee=this.employeeRepo.findById(userIds).orElseThrow(()->new ResourceNotFoundException("Employee","id",id));
        this.employeeRepo.delete(employee);
    }


    // update employee
    @Override
    public EmployeeResponseDto updateEmployeeById(EmployeeUpdateDto employeeDto, String id) {
        UUID userIds = null;
        try {
            userIds = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalUUIDException();
        }
        Employee employee=this.employeeRepo.findById(userIds).orElseThrow(()->new ResourceNotFoundException("Employee","id",id));
        if(employeeDto.getEmployeeName() != null && !employeeDto.getEmployeeName().isEmpty()){
            employee.setEmployeeName(employeeDto.getEmployeeName());
        }
        if(employeeDto.getEmail() != null && !employeeDto.getEmail().isEmpty()){
            employee.setEmail(employeeDto.getEmail());
        }
        if(employeeDto.getPhoneNumber() != null && !employeeDto.getPhoneNumber().isEmpty()){
            employee.setPhoneNumber(employeeDto.getPhoneNumber());
        }
        if(employeeDto.getReportTo() != null && !employeeDto.getReportTo().toString().isEmpty()){
            employee.setReportTo(employeeDto.getReportTo());
        }
        if(employeeDto.getProfileImage() != null && !employeeDto.getProfileImage().isEmpty()){
            employee.setProfileImage(employeeDto.getProfileImage());
        }
        Employee updatedEmployee=this.employeeRepo.save(employee);
        EmployeeResponseDto employeeResponseDto=new EmployeeResponseDto(updatedEmployee.getId());
        return employeeResponseDto;
    }


    // get nth level manager of an employee
    @Override
    public EmployeeOutputDto getNthLevelManagerOfEmployee(String id, int n) {
        if(n<1){
            throw new InvalidPageOrLevelException("Level must be greater than 0");
        }
        UUID userIds = null;
        try {
            userIds = UUID.fromString(id);
        } catch (IllegalArgumentException e) {
            throw new IllegalUUIDException();
        }
        UUID curId = userIds;
        Employee employee = null;
        String suffix;
        switch (n){
            case 1:
                suffix = "st";
                break;
            case 2:
                suffix = "nd";
                break;
            case 3:
                suffix = "rd";
                break;
            default:
                suffix = "th";
        }
        for(int i=0;i<=n;i++){
            if(curId == null){
                throw new ManagerNotFoundException(n,id,suffix);
            }
            employee = this.employeeRepo.findById(curId).orElseThrow(()->new ManagerNotFoundException(n,id,suffix));
            curId = employee.getReportTo();
        }
        return this.modelMapper.map(employee, EmployeeOutputDto.class);
    }
}
